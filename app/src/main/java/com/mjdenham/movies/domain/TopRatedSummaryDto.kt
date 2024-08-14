package com.mjdenham.movies.domain

data class TopRatedSummaryDto(
    val page: Int,
    val totalPages: Int,
    val movies: List<MovieDto>
) {
    data class MovieDto(
        val id: Long,
        val name: String,
        val voteAveragePc: Int,
        val smallPosterPath: String?
    )
}