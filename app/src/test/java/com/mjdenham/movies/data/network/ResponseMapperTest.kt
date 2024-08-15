package com.mjdenham.movies.data.network

import org.junit.Assert.*

import org.junit.Test

class ResponseMapperTest {

    private val mapper = ResponseMapper()

    private val TEST_MOVIE = MovieResultItem(
        adult = false,
        backdropPath = "backdropPath",
        genreIds = emptyList(),
        id = 3,
        originCountry = emptyList(),
        originalLanguage = "",
        originalName = "Original Name",
        overview = "Overview of movie",
        popularity = 0.1,
        posterPath = "posterPath.jpg",
        firstAirDate = "",
        name = "Name",
        voteAverage = 8.91,
        voteCount = 2
    )

    @Test
    fun mappingToTopRatedDtoShouldBeValid() {
        val response = TopRatedResponse(5, listOf(TEST_MOVIE), 10, 1)
        val dto = mapper.mapToTopRatedDto(response)
        assertEquals(5, dto.page)
        assertEquals(1, dto.movies.size)
        assertEquals("Name", dto.movies[0].name)
        assertEquals(89, dto.movies[0].voteAveragePc)
        assertTrue(dto.movies[0].smallPosterPath!!.endsWith("posterPath.jpg"))
    }

    @Test
    fun mappingToSimilarDtoShouldBeValid() {
        val response = SimilarResponse(5, listOf(TEST_MOVIE), 10, 1)
        val dto = mapper.mapToSimilarDto(response)
        assertEquals(1, dto.movies.size)
        assertEquals("Name", dto.movies[0].name)
        assertEquals("Overview of movie", dto.movies[0].overview)
        assertEquals(89, dto.movies[0].voteAveragePc)
        assertTrue(dto.movies[0].largePosterPath!!.endsWith("posterPath.jpg"))
    }
}