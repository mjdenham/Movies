package com.mjdenham.movies.ui.toprated

import androidx.paging.PagingSource
import com.mjdenham.movies.domain.MovieDto
import com.mjdenham.movies.domain.MoviesApi
import com.mjdenham.movies.domain.MoviesUseCase
import com.mjdenham.movies.domain.TopRatedSummaryDto
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MoviesPagingSourceTest {

    private val testMovie = MovieDto(1, "Spiderman", 9, "posterPath")

    private val moviesApiStub = object : MoviesApi {
        override suspend fun getTopRated(page: Int): TopRatedSummaryDto {
            return TopRatedSummaryDto(
                1,
                1,
                listOf(testMovie)
            )
        }
    }

    private val loadParamsRefresh = PagingSource.LoadParams.Refresh(
        key = 0,
        loadSize = 1,
        placeholdersEnabled = false
    )

    private val loadParamsAppend = PagingSource.LoadParams.Append(
        key = 1,
        loadSize = 1,
        placeholdersEnabled = false
    )

    private lateinit var pagingSource: MoviesPagingSource

    @Before
    fun setup() {
        pagingSource = MoviesPagingSource(MoviesUseCase(moviesApiStub))
    }

    @Test
    fun refreshShouldLoadData() = runTest {
        val loadResult: PagingSource.LoadResult<Int, MovieDto> = pagingSource.load(loadParamsRefresh)
        val pageResult = loadResult as PagingSource.LoadResult.Page<Int, MovieDto>
        assertEquals(1, pageResult.nextKey)
        assertEquals(1, pageResult.data.size)
        assertEquals(testMovie, pageResult.data[0])
    }

    @Test
    fun appendShouldStopAtEnd() = runTest {
        val loadResult: PagingSource.LoadResult<Int, MovieDto> = pagingSource.load(loadParamsAppend)
        val pageResult = loadResult as PagingSource.LoadResult.Page<Int, MovieDto>
        assertEquals(null, pageResult.nextKey)
        assertEquals(1, pageResult.data.size)
        assertEquals(testMovie, pageResult.data[0])
    }
}