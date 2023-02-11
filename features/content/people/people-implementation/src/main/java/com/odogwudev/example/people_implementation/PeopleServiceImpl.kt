package com.odogwudev.example.people_implementation

import com.odogwudev.example.pagination_api.Pager
import com.odogwudev.example.pagination_api.PagerItem
import com.odogwudev.example.pagination_api.RefreshType
import com.odogwudev.example.people_api.PeopleService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class PeopleServiceImpl(
    private val remoteSource: PeopleRemoteSource,
    private val pager: Pager
) : PeopleService {

    private val _popularPeople = MutableStateFlow<List<PagerItem>>(value = emptyList())
    override val popularPeople: Flow<List<PagerItem>> = _popularPeople

    override suspend fun getPopularPeople(refreshType: RefreshType): List<PagerItem> = pager.paginate(
        refreshType = refreshType,
        flow = _popularPeople,
        getRemoteContent = { page -> remoteSource.getPopularPeople(page = page) },
        cacheWithError = false
    )

    override fun clearPopularPeople() {
        _popularPeople.value = emptyList()
    }
}