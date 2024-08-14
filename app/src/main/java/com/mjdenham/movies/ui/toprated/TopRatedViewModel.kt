package com.mjdenham.movies.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.mjdenham.movies.domain.TopRatedSummaryDto
import kotlinx.coroutines.flow.Flow

class TopRatedViewModel(val moviesPagingSource: PagingSource<Int, TopRatedSummaryDto.MovieDto> = MoviesPagingSource()): ViewModel() {

    private val pagingDataFlow = getMoviesPager().cachedIn(viewModelScope)

    fun getPagedMovies(): Flow<PagingData<TopRatedSummaryDto.MovieDto>> = pagingDataFlow

    private fun getMoviesPager() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            moviesPagingSource
        }
    ).flow
}