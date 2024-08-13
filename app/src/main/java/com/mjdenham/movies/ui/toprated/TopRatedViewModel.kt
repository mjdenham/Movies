package com.mjdenham.movies.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjdenham.movies.domain.Movies
import com.mjdenham.movies.domain.TopRatedSummaryDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopRatedViewModel(val movies: Movies = Movies(), val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _topRatedState = MutableStateFlow<List<TopRatedSummaryDto.MovieDto>>(emptyList())
    val topRatedState: StateFlow<List<TopRatedSummaryDto.MovieDto>> = _topRatedState.asStateFlow()

    init {
        loadTopratedMovies()
    }

    fun loadTopratedMovies() {
        viewModelScope.launch(dispatcher) {
            val topRatedMovies = movies.getTopRatedMovies(1)
            _topRatedState.value = topRatedMovies.movies
        }
    }

}