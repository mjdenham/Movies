package com.mjdenham.movies.domain

interface MoviesApi {
    suspend fun getTopRated(page: Int = 1): TopRatedSummaryDto
}