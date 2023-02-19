package com.odogwudev.example.base

sealed class UserAction {
    object SwipeRefresh : UserAction()
    object Search : UserAction()
    object Error : UserAction()
    object Normal : UserAction()
}