package com.mjdenham.movies.domain

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MoviesUseCaseTest {

    private val testMovie = MovieDto(1, "Spiderman", "Brief overview of the movie", 9, "smallPosterPath", "posterPath")
    private val similarMovie = MovieDto(2, "Spiderman 2", "Another brief overview of the movie", 9, "smallPosterPath", "posterPath")

    private val moviesApiStub = object : MoviesApi {
        override suspend fun getTopRatedMovies(page: Int): TopRatedMoviesDto {
            return TopRatedMoviesDto(
                1,
                1,
                listOf(testMovie)
            )
        }

        override suspend fun getSimilarMovies(movieId: Int): SimilarMoviesDto {
            return SimilarMoviesDto(
                listOf(similarMovie)
            )
        }
    }

    private val moviesUseCase = MoviesUseCase(moviesApiStub)

    @Test
    fun getTopRatedMovies() = runTest {
        val topRatedMovies = moviesUseCase.getTopRatedMovies(1)
        assertEquals(testMovie, topRatedMovies.movies[0])
        assertEquals(1, topRatedMovies.page)
    }

    @Test
    fun getSimilarMovies() = runTest {
        val similarMovies = moviesUseCase.getSimilarMovies(testMovie)

        assertEquals(listOf(testMovie, similarMovie), similarMovies.movies)
    }
}