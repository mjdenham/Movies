package com.mjdenham.movies.ui.similar

import com.mjdenham.movies.domain.Movie

sealed interface SimilarResponse {
    data class Success(val data: List<Movie>) : SimilarResponse
    object Loading : SimilarResponse
    object Error : SimilarResponse
}