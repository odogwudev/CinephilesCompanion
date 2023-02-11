package com.odogwudev.example.api

import android.app.Activity

interface ActivityProvider {
    fun get(): Activity
}