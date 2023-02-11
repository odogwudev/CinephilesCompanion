package com.odogwudev.example.tv.model

import com.odogwudev.example.pagination_api.PagerItem

data class TvSeries(
    override val id: String,
    val backdropPath: String,
    val genreIds: List<String>,
    val originalCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: String,
    val posterPath: String?,
    val firstAirDate: String,
    val voteAverage: String
) : PagerItem
