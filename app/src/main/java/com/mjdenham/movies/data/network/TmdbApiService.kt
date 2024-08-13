package com.mjdenham.movies.data.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbApiService {
    @GET("top_rated")
    suspend fun getTopRated(@Query("page") page: Int, @Header("Authorization")apiToken: String): TopRatedResponse
}