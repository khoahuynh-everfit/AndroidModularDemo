package com.example.everfitmodular

import android.app.Activity
import android.content.Intent
import com.example.core.CoreApplication
import com.example.modular_login.LoginActivity
import com.example.modular_login.ModularLoginNavigation
import com.example.modular_main.MainActivity
import com.example.modular_main.ModularMainNavigation
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EverfitApplication : CoreApplication(), ModularLoginNavigation, ModularMainNavigation {

    override fun onLogout(activity: MainActivity) {
        activity.startActivity(Intent(activity, LoginActivity::class.java))
        activity.finish()
    }

    override fun navigateLoginToMain(activity: Activity) {
        activity.startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }

    override fun navigateLoginToForgotPassword(activity: Activity) {
    }
}