package com.odogwudev.example.people.model

import com.odogwudev.example.pagination_api.PagerItem

data class People(
    override val id: String,
    val adult: Boolean,
    val profilePath: String,
    val name: String
) : PagerItem