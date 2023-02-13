package com.odogwudev.example.domain_usecase.screen.explore.search

import com.odogwudev.example.domain_usecase.screen.explore.Category
import com.odogwudev.example.domain_usecase.helper.movie.search.DeleteSearchMovieUseCase
import com.odogwudev.example.domain_usecase.helper.tvSeries.search.DeleteSearchTvSeriesUseCase

class DeleteContentUseCase(
    private val deleteSearchMovieUseCase: DeleteSearchMovieUseCase,
    private val deleteSearchTvSeriesUseCase: DeleteSearchTvSeriesUseCase
) {

    operator fun invoke(category: Category) = when (category) {
        Category.MOVIE -> deleteSearchMovieUseCase()
        Category.TV -> deleteSearchTvSeriesUseCase()
    }
}