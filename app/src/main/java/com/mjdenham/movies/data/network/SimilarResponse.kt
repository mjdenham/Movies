package com.mjdenham.movies.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimilarResponse(
    val page: Int,
    val results: List<MovieResultItem>,
    @SerialName("total_pages")
    val totalPages: Int?,
    @SerialName("total_results")
    val totalResults: Long?,
)