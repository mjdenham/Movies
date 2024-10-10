package com.mjdenham.movies.domain

class GetTopRatedMoviesUseCase(private val moviesApi: MoviesApi) {
    suspend fun getTopRatedMovies(page: Int): TopRatedMovies = moviesApi.getTopRatedMovies(page)
}