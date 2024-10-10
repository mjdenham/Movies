package com.mjdenham.movies.ui.similar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mjdenham.movies.domain.GetSimilarMoviesUseCase
import com.mjdenham.movies.domain.Movie
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SimilarViewModel(private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): ViewModel() {

    private val _similarState = MutableStateFlow<SimilarResponse>(SimilarResponse.Loading)
    val similarState: StateFlow<SimilarResponse> = _similarState.asStateFlow()

    fun loadSimilarMovies(movie: Movie) {
        _similarState.value = SimilarResponse.Loading
        viewModelScope.launch(dispatcher) {
            try {
                val similarMovies = getSimilarMoviesUseCase.getSimilarMovies(movie)
                _similarState.value = SimilarResponse.Success(similarMovies.movies)
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching similar movies")
                _similarState.value = SimilarResponse.Error
            }
        }
    }

    companion object {
        private const val TAG = "SimilarViewModel"
    }
}