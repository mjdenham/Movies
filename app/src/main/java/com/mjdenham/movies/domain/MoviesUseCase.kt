package com.mjdenham.movies.domain

class MoviesUseCase(private val moviesApi: MoviesApi) {
    suspend fun getTopRatedMovies(page: Int): TopRatedSummaryDto = moviesApi.getTopRated(page)
}