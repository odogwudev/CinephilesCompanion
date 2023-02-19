package com.odogwudev.example.seeall

import androidx.compose.runtime.*
import com.odogwudev.example.base.BaseScreenState
import com.odogwudev.example.base.UserAction
import com.odogwudev.example.domain_model.ContentItem
import com.odogwudev.example.domain_usecase.screen.listall.GetSeeAllScreenFlowUseCase
import com.odogwudev.example.domain_usecase.screen.listall.GetSeeAllScreenUseCase
import com.odogwudev.example.domain_util.result.Result
import com.odogwudev.example.pagination_api.RefreshType
import com.odogwudev.example.utils.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun rememberSeeAllScreenState(
    contentType: String,
    getSeeAllScreenUseCase: GetSeeAllScreenUseCase = get(),
    getSeeAllScreenFlowUseCase: GetSeeAllScreenFlowUseCase = get()
) = remember {
    SeeAllScreenState(
        contentType = contentType,
        getSeeAllScreenUseCase = getSeeAllScreenUseCase,
        getSeeAllScreenFlowUseCase = getSeeAllScreenFlowUseCase
    )
}

class SeeAllScreenState(
    val contentType: String,
    private val getSeeAllScreenUseCase: GetSeeAllScreenUseCase,
    private val getSeeAllScreenFlowUseCase: GetSeeAllScreenFlowUseCase
) : BaseScreenState() {

    var action by mutableStateOf<Event<Action>?>(value = null)
        private set
    var watchableItems by mutableStateOf<List<ContentItem>>(value = emptyList())
        private set

    init {
        getSeeAllScreenFlowUseCase(contentType = contentType).onEach {
            watchableItems = it
        }.launchIn(scope = scope)
    }

    override fun getScreenData(userAction: UserAction, delay: Long) {
        if (state !in listOf(State.Loading, State.SwipeRefresh)) {
            scope.launch {
                state = when (userAction) {
                    UserAction.SwipeRefresh -> State.SwipeRefresh
                    else -> State.Normal
                }
                state = when (
                    val result = getSeeAllScreenUseCase(
                        contentType = contentType,
                        refreshType = when {
                            userAction is UserAction.SwipeRefresh -> RefreshType.FORCE_REFRESH
                            watchableItems.isEmpty() -> RefreshType.CACHE_IF_POSSIBLE
                            else -> RefreshType.NEXT_PAGE
                        }
                    )
                ) {
                    is Result.Failure -> when {
                        userAction is UserAction.SwipeRefresh -> State.ShowSnackBar
                        watchableItems.isNotEmpty() -> State.Normal
                        else -> State.Error(message = result.exception.message.orEmpty())
                    }
                    is Result.Success -> State.Normal
                }
            }
        }
    }

    fun onUpClicked() {
        action = Event(data = Action.NavigateUp)
    }

    sealed class Action {
        object NavigateUp : Action()
    }
}