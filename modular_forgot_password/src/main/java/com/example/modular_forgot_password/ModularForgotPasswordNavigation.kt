package com.example.modular_forgot_password

import android.app.Activity
import com.example.core.CoreNavigation

interface ModularForgotPasswordNavigation : CoreNavigation {

    fun navigateForgotPasswordToLogin(activity: Activity)

}