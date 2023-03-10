package com.odogwudev.example.domain_util

enum class FilterType(val value: String) {
    DEFAULT(value = "popularity.desc"),
    LATEST_RELEASE(value = "release_date.desc"),
    VOTE_AVERAGE(value = "vote_average.desc"),
}