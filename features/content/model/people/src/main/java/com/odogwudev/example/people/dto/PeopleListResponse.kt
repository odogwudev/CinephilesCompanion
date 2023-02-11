package com.odogwudev.example.people.dto

import com.odogwudev.example.network_api.DataLayerException
import com.odogwudev.example.people.model.People
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeopleListResponse(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val results: List<PeopleResponse>?
)

fun PeopleListResponse.toModel() : List<People> {
    if (page == null || results == null) {
        throw DataLayerException(message = "PeopleListException: $this")
    }
    return results.mapNotNull { it.toModel() }
}