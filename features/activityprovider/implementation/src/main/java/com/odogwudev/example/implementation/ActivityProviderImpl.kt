package com.odogwudev.example.implementation

import android.app.Activity
import com.odogwudev.example.api.ActivityProvider
import com.odogwudev.example.api.ActivitySetter

class ActivityProviderImp : ActivitySetter, ActivityProvider {
    private var activity: Activity? = null

    override fun set(activity: Activity) {
        this.activity = activity
    }

    override fun clear() {
        activity = null
    }

    override fun get(): Activity {
        return activity ?: throw Throwable("Activity does not exists")
    }
}
