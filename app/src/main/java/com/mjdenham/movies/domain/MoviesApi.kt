package com.mjdenham.movies.domain

interface MoviesApi {
    suspend fun getTopRatedMovies(page: Int = 1): TopRatedMoviesDto
    suspend fun getSimilarMovies(movieId: Int): SimilarMoviesDto
}