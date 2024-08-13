package com.mjdenham.movies.domain

import com.mjdenham.movies.data.network.TmdbApiClient

class Movies(val moviesApi: MoviesApi = TmdbApiClient()) {
    suspend fun getTopRatedMovies(page: Int): TopRatedSummaryDto = moviesApi.getTopRated(page)
}