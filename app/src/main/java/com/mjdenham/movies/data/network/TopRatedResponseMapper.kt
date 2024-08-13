package com.mjdenham.movies.data.network

import com.mjdenham.movies.domain.TopRatedSummaryDto
import kotlin.math.roundToInt

class TopRatedResponseMapper {
    fun mapToTopRatedDto(topRatedResponse: TopRatedResponse): TopRatedSummaryDto =
        TopRatedSummaryDto(topRatedResponse.page, topRatedResponse.totalPages, topRatedResponse.results.map(::mapToMovieDto))

    private fun mapToMovieDto(movieResult: TopRatedResponse.MovieResult): TopRatedSummaryDto.MovieDto =
        TopRatedSummaryDto.MovieDto(movieResult.id, movieResult.name, movieResult.voteAverage.roundToInt(), smallPosterPathFrom(movieResult.posterPath))

    private fun smallPosterPathFrom(posterPath: String): String = SMALL_POSTER_PREFIX + posterPath

    companion object {
        private const val SMALL_POSTER_PREFIX = "https://image.tmdb.org/t/p/w92/"
    }
}