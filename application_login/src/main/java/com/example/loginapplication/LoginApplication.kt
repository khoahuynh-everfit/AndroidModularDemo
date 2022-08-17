package com.example.loginapplication

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.component.utils.launchActivity
import com.example.core.CoreApplication
import com.example.modular_forgot_password.ForgotPasswordActivity
import com.example.modular_login.LoginActivity
import com.example.modular_login.ModularLoginNavigation
import com.example.modular_splash.ModularSplashNavigation
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoginApplication : CoreApplication(), ModularLoginNavigation, ModularSplashNavigation {

    override fun navigateSplashToLogin(activity: Activity) {
        activity.launchActivity<LoginActivity>()
        activity.finish()
    }

    override fun navigateSplashToMain(activity: Activity) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, "navigateSplashToMain", Toast.LENGTH_SHORT).show()
        }
    }

    override fun navigateSplashToWaitingActivation(activity: Activity) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, "navigateSplashToWaitingActivation", Toast.LENGTH_SHORT).show()
        }
    }

    override fun navigateLoginToForgotPassword(activity: Activity) {
        activity.launchActivity<ForgotPasswordActivity>()
    }

    override fun navigateLoginToMain(activity: Activity) {
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show()
        }
    }
}