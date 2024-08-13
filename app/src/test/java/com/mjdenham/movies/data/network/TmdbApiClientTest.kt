package com.mjdenham.movies.data.network

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TmdbApiClientTest {

    private var tmdbApiClient = TmdbApiClient()

    @Test
    fun getTopRated() {
        runBlocking {
            val movies = tmdbApiClient.getTopRated(2)
            assertEquals(2, movies.page)
            assertNotNull(movies.results[0].name)
            assert(movies.results.size > 10)
        }
    }
}