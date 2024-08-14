package com.mjdenham.movies.ui.toprated

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mjdenham.movies.domain.MoviesUseCase
import com.mjdenham.movies.domain.TopRatedSummaryDto

class MoviesPagingSource(
    val moviesUseCase: MoviesUseCase = MoviesUseCase(),
): PagingSource<Int, TopRatedSummaryDto.MovieDto>() {

    override fun getRefreshKey(state: PagingState<Int, TopRatedSummaryDto.MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TopRatedSummaryDto.MovieDto> {
        return try {
            val page = params.key ?: 1
            val response = moviesUseCase.getTopRatedMovies(page)

            LoadResult.Page(
                data = response.movies,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.movies.isEmpty() || response.totalPages == page) null else page.plus(1),
            )
        } catch (e: Exception) {
            Log.e(TAG,"Error loading movies", e)
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "MoviesPagingSource"
    }
}