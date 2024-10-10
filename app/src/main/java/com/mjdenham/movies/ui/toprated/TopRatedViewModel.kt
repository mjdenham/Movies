package com.mjdenham.movies.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mjdenham.movies.domain.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.plus

class TopRatedViewModel(
    private val topRatedPagingSource: TopRatedPagingSource,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val pagingDataFlow = getMoviesPager().cachedIn(viewModelScope + dispatcher)

    fun getPagedMovies(): Flow<PagingData<Movie>> = pagingDataFlow

    private fun getMoviesPager() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            topRatedPagingSource
        }
    ).flow
}