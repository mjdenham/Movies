package com.mjdenham.movies.data.network

import com.mjdenham.movies.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class TmdbApiClient {
    suspend fun getMovies(page: Int = 1) = service.getMovies(page, TMDB_API_TOKEN)

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(jsonIgnoreUnknownKeys.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val service: TmdbApiService = retrofit.create(TmdbApiService::class.java)

    companion object {
        private val jsonIgnoreUnknownKeys = Json { ignoreUnknownKeys = true }
        private const val BASE_URL = "https://api.themoviedb.org/3/tv/"
        private const val TMDB_API_TOKEN = "Bearer "+BuildConfig.TMDB_API_TOKEN
    }
}