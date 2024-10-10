package com.mjdenham.movies.domain

interface MoviesApi {
    suspend fun getTopRatedMovies(page: Int = 1): TopRatedMovies
    suspend fun getSimilarMovies(movieId: Int): SimilarMovies
}