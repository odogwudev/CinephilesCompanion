package com.odogwudev.example.utils

val movieGenres = buildMap {
    put(key = 28, value = "Action")
    put(key = 12, value = "Adventure")
    put(key = 14, value = "Fantasy")
    put(key = 16, value = "Animation")
    put(key = 27, value = "Horror")
    put(key = 35, value = "Comedy")
    put(key = 36, value = "History")
    put(key = 80, value = "Crime")
    put(key = 99, value = "Documentary")
    put(key = 18, value = "Drama")
    put(key = 10751, value = "Family")
    put(key = 10402, value = "Music")
    put(key = 9648, value = "Mystery")
    put(key = 10749, value = "Romance")
    put(key = 878, value = "Sci-Fi")
    put(key = 10765, value = "Tv Movie")
    put(key = 53, value = "Thriller")
    put(key = 10752, value = "War")
    put(key = 37, value = "Western")
}

val tvGenres = buildMap {
    put(key = 10759, value = "Action & Adventure")
    put(key = 16, value = "Animation")
    put(key = 35, value = "Comedy")
    put(key = 80, value = "Crime")
    put(key = 99, value = "Documentary")
    put(key = 18, value = "Drama")
    put(key = 10751, value = "Family")
    put(key = 10762, value = "Kids")
    put(key = 9648, value = "Mystery")
    put(key = 10763, value = "News")
    put(key = 10764, value = "Reality")
    put(key = 10765, value = "Sci-Fi & Fantasy")
    put(key = 10766, value = "Soap")
    put(key = 10767, value = "Talk")
    put(key = 10768, value = "War & Politics")
    put(key = 37, value = "Western")
}

val jointGenres = (movieGenres.keys + tvGenres.keys).associateWith {
    setOf(movieGenres[it], tvGenres[it]).filterNotNull().joinToString()
}