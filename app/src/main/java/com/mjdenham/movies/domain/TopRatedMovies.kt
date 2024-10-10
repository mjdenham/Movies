package com.mjdenham.movies.domain

data class TopRatedMovies(
    val page: Int,
    val totalPages: Int,
    val movies: List<Movie>
)