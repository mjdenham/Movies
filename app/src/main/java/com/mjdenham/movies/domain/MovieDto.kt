package com.mjdenham.movies.domain

import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int,
    val name: String,
    val overview: String,
    val voteAveragePc: Int,
    val smallPosterPath: String?,
    val largePosterPath: String?
)