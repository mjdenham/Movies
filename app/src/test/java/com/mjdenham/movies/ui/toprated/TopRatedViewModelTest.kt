package com.mjdenham.movies.ui.toprated

import com.mjdenham.movies.domain.Movies
import com.mjdenham.movies.domain.MoviesApi
import com.mjdenham.movies.domain.TopRatedSummaryDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TopRatedViewModelTest {

    private val testMovie = TopRatedSummaryDto.MovieDto(1, "Spiderman", 9, "posterPath")

    private val moviesApiStub = object : MoviesApi {
        override suspend fun getTopRated(page: Int): TopRatedSummaryDto {
            return TopRatedSummaryDto(
                1,
                2,
                listOf(testMovie)
            )
        }
    }

    @Test
    fun shouldLoadMoviesOnInitialisation() = runBlocking {
        val viewModel = TopRatedViewModel(Movies(moviesApiStub), UnconfinedTestDispatcher())
        val movieDtos = viewModel.topRatedState.value
        assertEquals(testMovie.name, movieDtos[0].name)
    }
}