package com.odogwudev.example.movie.dto

import com.odogwudev.example.movie.model.Movie
import com.odogwudev.example.network_api.DataLayerException
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<MovieResponse>?
)

fun MovieListResponse.toModel() : List<Movie> {
    if (page == null || results == null) {
        throw DataLayerException(message = "MovieListException: $this")
    }
    return results.mapNotNull { it.toModel() }
}
