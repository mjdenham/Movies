package com.mjdenham.movies.domain

data class TopRatedMoviesDto(
    val page: Int,
    val totalPages: Int,
    val movies: List<MovieDto>
)