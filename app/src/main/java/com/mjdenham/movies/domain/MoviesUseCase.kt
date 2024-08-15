package com.mjdenham.movies.domain

class MoviesUseCase(private val moviesApi: MoviesApi) {
    suspend fun getTopRatedMovies(page: Int): TopRatedMoviesDto = moviesApi.getTopRated(page)
}