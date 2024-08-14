package com.mjdenham.movies.domain

data class TopRatedSummaryDto(
    val page: Int,
    val totalPages: Int,
    val movies: List<MovieDto>
)