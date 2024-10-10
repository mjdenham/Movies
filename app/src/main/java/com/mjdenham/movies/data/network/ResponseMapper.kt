package com.mjdenham.movies.data.network

import com.mjdenham.movies.domain.Movie
import com.mjdenham.movies.domain.SimilarMovies
import com.mjdenham.movies.domain.TopRatedMovies
import kotlin.math.roundToInt

class ResponseMapper {
    fun mapToTopRatedDto(topRatedResponse: TopRatedResponse): TopRatedMovies =
        TopRatedMovies(
            topRatedResponse.page,
            topRatedResponse.totalPages ?: 1,
            topRatedResponse.results.map(::mapToMovieDto)
        )

    fun mapToSimilarDto(similarResponse: SimilarResponse): SimilarMovies =
        SimilarMovies(
            similarResponse.results.map(::mapToMovieDto)
        )

    private fun mapToMovieDto(movieResult: MovieResultItem): Movie =
        Movie(
            movieResult.id,
            movieResult.name,
            movieResult.overview,
            voteAveragePercent(movieResult.voteAverage),
            smallPosterPathFrom(movieResult.posterPath),
            largePosterPathFrom(movieResult.posterPath)
        )

    private fun voteAveragePercent(voteAverage: Double) = (10.0 * voteAverage).roundToInt()

    private fun smallPosterPathFrom(posterPath: String?): String? = posterPath?.let {
        SMALL_POSTER_PREFIX + posterPath
    }

    private fun largePosterPathFrom(posterPath: String?): String? = posterPath?.let {
        LARGE_POSTER_PREFIX + posterPath
    }

    companion object {
        private const val SMALL_POSTER_PREFIX = "https://image.tmdb.org/t/p/w92/"
        private const val LARGE_POSTER_PREFIX = "https://image.tmdb.org/t/p/w780/"
    }
}