package com.mjdenham.movies.ui.similar

import com.mjdenham.movies.domain.MovieDto

sealed interface SimilarResponse {
    data class Success(val data: List<MovieDto>) : SimilarResponse
    object Loading : SimilarResponse
    object Error : SimilarResponse
}