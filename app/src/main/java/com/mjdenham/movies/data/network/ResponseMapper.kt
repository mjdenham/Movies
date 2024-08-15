package com.mjdenham.movies.data.network

import com.mjdenham.movies.domain.MovieDto
import com.mjdenham.movies.domain.SimilarMoviesDto
import com.mjdenham.movies.domain.TopRatedMoviesDto
import kotlin.math.roundToInt

class ResponseMapper {
    fun mapToTopRatedDto(topRatedResponse: TopRatedResponse): TopRatedMoviesDto =
        TopRatedMoviesDto(
            topRatedResponse.page,
            topRatedResponse.totalPages ?: 1,
            topRatedResponse.results.map(::mapToMovieDto)
        )

    fun mapToSimilarDto(similarResponse: SimilarResponse): SimilarMoviesDto =
        SimilarMoviesDto(
            similarResponse.results.map(::mapToMovieDto)
        )

    private fun mapToMovieDto(movieResult: MovieResultItem): MovieDto =
        MovieDto(
            movieResult.id,
            movieResult.name,
            movieResult.overview,
            voteAveragePercent(movieResult.voteAverage),
            smallPosterPathFrom(movieResult.posterPath),
            posterPathFrom(movieResult.posterPath)
        )

    private fun voteAveragePercent(voteAverage: Double) = (10.0 * voteAverage).roundToInt()

    private fun smallPosterPathFrom(posterPath: String?): String? = posterPath?.let {
        SMALL_POSTER_PREFIX + posterPath
    }

    private fun posterPathFrom(posterPath: String?): String? = posterPath?.let {
        POSTER_PREFIX + posterPath
    }

    companion object {
        private const val SMALL_POSTER_PREFIX = "https://image.tmdb.org/t/p/w92/"
        private const val POSTER_PREFIX = "https://image.tmdb.org/t/p/original/"
    }
}