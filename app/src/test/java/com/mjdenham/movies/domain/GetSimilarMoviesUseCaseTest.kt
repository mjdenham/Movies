package com.mjdenham.movies.domain

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetSimilarMoviesUseCaseTest {

    private val testMovie = Movie(1, "Spiderman", "Brief overview of the movie", 9, "smallPosterPath", "posterPath")
    private val similarMovie = Movie(2, "Spiderman 2", "Another brief overview of the movie", 9, "smallPosterPath", "posterPath")

    private val moviesApiStub = object : MoviesApi {
        override suspend fun getTopRatedMovies(page: Int): TopRatedMovies {
            return TopRatedMovies(
                1,
                1,
                listOf(testMovie)
            )
        }

        override suspend fun getSimilarMovies(movieId: Int): SimilarMovies {
            return SimilarMovies(
                listOf(similarMovie)
            )
        }
    }

    private val getSimilarMoviesUseCase = GetSimilarMoviesUseCase(moviesApiStub)

    @Test
    fun getSimilarMovies() = runTest {
        val similarMovies = getSimilarMoviesUseCase.getSimilarMovies(testMovie)

        assertEquals(listOf(testMovie, similarMovie), similarMovies.movies)
    }
}