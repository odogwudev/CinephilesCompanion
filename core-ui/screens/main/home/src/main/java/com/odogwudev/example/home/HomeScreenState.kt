package com.odogwudev.example.home

import androidx.compose.runtime.*
import com.odogwudev.example.base.BaseScreenState
import com.odogwudev.example.base.UserAction
import com.odogwudev.example.domain_model.HomeScreenContent
import com.odogwudev.example.domain_model.isEmpty
import com.odogwudev.example.domain_usecase.screen.home.GetHomeScreenFlowUseCase
import com.odogwudev.example.domain_usecase.screen.home.GetHomeScreenUseCase
import com.odogwudev.example.domain_util.result.Result
import com.odogwudev.example.pagination_api.RefreshType
import com.odogwudev.example.utils.Event
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.compose.get

@Composable
fun rememberHomeScreenState(
    getHomeScreen: GetHomeScreenUseCase = get(),
    getHomeScreenFlow: GetHomeScreenFlowUseCase = get()
): HomeScreenState = remember {
    HomeScreenState(
        getHomeScreen = getHomeScreen,
        getHomeScreenFlow = getHomeScreenFlow
    )
}

class HomeScreenState(
    private val getHomeScreen: GetHomeScreenUseCase,
    private val getHomeScreenFlow: GetHomeScreenFlowUseCase,
) : BaseScreenState() {

    var action by mutableStateOf<Event<Action>?>(value = null)
        private set
    var homeContent by mutableStateOf(value = HomeScreenContent.createEmptyHomeScreenContent())
        private set

    init {
        getHomeScreenFlow().onEach {
            homeContent = it
        }.launchIn(scope = scope)
        getScreenData(userAction = UserAction.Normal)
    }

    override fun getScreenData(userAction: UserAction, delay: Long) {
        state = when (userAction) {
            UserAction.SwipeRefresh -> State.SwipeRefresh
            else -> State.Loading
        }
        scope.launch {
            state = when (
                val result = getHomeScreen(
                    coroutineScope = this,
                    refreshType = when (userAction) {
                        is UserAction.SwipeRefresh -> RefreshType.FORCE_REFRESH
                        else -> RefreshType.CACHE_IF_POSSIBLE
                    }
                )
            ) {
                is Result.Failure -> when {
                    userAction is UserAction.Normal && homeContent.isEmpty() ->
                        State.Error(message = result.exception.message.orEmpty())
                    userAction is UserAction.SwipeRefresh ->
                        State.ShowSnackBar
                    else -> State.Error(message = result.exception.message.orEmpty())
                }
                is Result.Success -> State.Normal
            }
        }
    }

    fun onSeeAllPopularMoviesClicked() {
        action = Event(data = Action.SeeAllPopularMovies)
    }

    fun onSeeAllPopularPeopleClicked() {
        action = Event(data = Action.SeeAllPopularPeople)
    }

    fun onSeeAllNowPlayingMoviesClicked() {
        action = Event(data = Action.SeeAllNowPlayingMovies)
    }

    fun onSeeAllTopRatedMoviesClicked() {
        action = Event(data = Action.SeeAllTopRatedMovies)
    }

    sealed class Action {
        object SeeAllPopularMovies : Action()
        object SeeAllPopularPeople : Action()
        object SeeAllNowPlayingMovies : Action()
        object SeeAllTopRatedMovies : Action()
    }
}