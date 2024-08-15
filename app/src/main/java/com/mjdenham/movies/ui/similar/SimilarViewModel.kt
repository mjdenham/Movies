package com.mjdenham.movies.ui.similar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjdenham.movies.domain.MovieDto
import com.mjdenham.movies.domain.MoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SimilarViewModel(val moviesUseCase: MoviesUseCase, val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _similarState = MutableStateFlow<List<MovieDto>>(emptyList())
    val similarState: StateFlow<List<MovieDto>> = _similarState.asStateFlow()

    fun loadSimilarMovies(movie: MovieDto) {
        viewModelScope.launch(dispatcher) {
            val similarMovies = moviesUseCase.getSimilarMovies(movie)
            _similarState.value = similarMovies.movies
        }
    }
}