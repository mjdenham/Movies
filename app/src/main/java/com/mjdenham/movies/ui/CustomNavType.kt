package com.mjdenham.movies.ui

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.mjdenham.movies.domain.MovieDto
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val movieDtoType = object: NavType<MovieDto> (
        isNullableAllowed = false
    ) {
        override fun parseValue(value: String): MovieDto {
            return Json.decodeFromString(Uri.decode(value))
        }
        override fun serializeAsValue(value: MovieDto): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun get(bundle: Bundle, key: String): MovieDto? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }
        override fun put(bundle: Bundle, key: String, value: MovieDto) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}