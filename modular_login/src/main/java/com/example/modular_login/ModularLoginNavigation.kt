package com.example.modular_login

import android.app.Activity
import com.example.core.CoreNavigation

interface ModularLoginNavigation : CoreNavigation {

    fun navigateLoginToMain(activity: Activity)

    fun navigateLoginToForgotPassword(activity: Activity)

}