package com.odogwudev.example.domain_model

import com.odogwudev.example.movie.model.Movie

fun HomeScreenContent.isEmpty(): Boolean =
    upcomingMovies.isEmpty() ||
            popularMovies.isEmpty() ||
            nowPlayingMovies.isEmpty() ||
            topRatedMovies.isEmpty() ||
            popularPeople.isEmpty()

data class HomeScreenContent(
    val upcomingMovies: List<Movie>,
    val popularMovies: List<ContentItem.Watchable>,
    val nowPlayingMovies: List<ContentItem.Watchable>,
    val topRatedMovies: List<ContentItem.Watchable>,
    val popularPeople: List<ContentItem.Person>
) {
    companion object {
        fun createEmptyHomeScreenContent() = HomeScreenContent(
            upcomingMovies = emptyList(),
            popularPeople = emptyList(),
            nowPlayingMovies = emptyList(),
            topRatedMovies = emptyList(),
            popularMovies = emptyList()
        )
    }
}