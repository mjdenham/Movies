package com.mjdenham.movies.domain

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Long,
    val name: String,
    val voteAveragePc: Int,
    val smallPosterPath: String?
)