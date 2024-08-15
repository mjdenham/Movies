package com.mjdenham.movies.data.network

import org.junit.Assert.*

import org.junit.Test

class TopRatedResponseMapperTest {

    private val mapper = TopRatedResponseMapper()

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
}