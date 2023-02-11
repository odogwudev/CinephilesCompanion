package com.odogwudev.example.api

import android.app.Activity

interface ActivitySetter {
    fun set(activity: Activity)
    fun clear()
}