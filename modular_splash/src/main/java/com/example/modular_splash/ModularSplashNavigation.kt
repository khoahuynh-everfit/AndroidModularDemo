package com.example.modular_splash

import android.app.Activity
import com.example.core.CoreNavigation

interface ModularSplashNavigation : CoreNavigation {

    fun navigateSplashToLogin(activity: Activity)

    fun navigateSplashToMain(activity: Activity)

    fun navigateSplashToWaitingActivation(activity: Activity)

}