package com.mjdenham.movies.data.network

import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TmdbApiClientTest {

    private var tmdbApiClient = TmdbApiClient()

    @Test
    fun topRatedApiShouldReturnValidResults() = runTest {
        val movies = tmdbApiClient.getTopRatedMovies(2)
        assertEquals(2, movies.page)
        assertNotNull(movies.movies[0].name)
        assert(movies.movies.size > 10)
    }

    @Test
    fun similarApiCallShouldReturnValidResults() = runTest {
        val similar = tmdbApiClient.getSimilarMovies(1396)
        val movies = similar.movies
        assert(movies.size > 10)
        assertNotNull(movies[0].name)
        assertNotNull(movies[0].overview)
    }
}