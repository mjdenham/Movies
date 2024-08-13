package com.mjdenham.movies.domain

data class TopRatedSummaryDto(
    val page: Long,
    val totalPages: Long,
    val movies: List<MovieDto>
) {
    data class MovieDto(
        val id: Long,
        val name: String,
        val voteAverage: Int,
        val smallPosterPath: String
    )
}