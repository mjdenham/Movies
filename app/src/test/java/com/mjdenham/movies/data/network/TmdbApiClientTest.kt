package com.mjdenham.movies.data.network

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class TmdbApiClientTest {

    private var tmdbApiClient = TmdbApiClient()
    @Before
    fun setUp() {
    }

    @Test
    fun getMovies() {
        runBlocking {
            val movies = tmdbApiClient.getMovies(2)
            assertEquals(2, movies.page)
        }
    }
}