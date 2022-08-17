package com.example.core

import android.app.Application

open class CoreApplication : Application(), CoreNavigation{

    companion object {
        @JvmStatic
        lateinit var instance: CoreApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}