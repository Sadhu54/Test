package com.test_app_21.app

import android.app.Application
import android.content.Intent

import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class TestApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TestApp.Companion.mInstance = this
    }

    companion object {
        private lateinit var mInstance: TestApp
        @Synchronized
        fun getInstance(): TestApp {
            return TestApp.Companion.mInstance
        }
    }
}