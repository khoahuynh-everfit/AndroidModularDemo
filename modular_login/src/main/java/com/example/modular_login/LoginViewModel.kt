package com.example.modular_login

import com.example.core.base.BaseViewModel
import com.example.data.usecase.authentication.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel() {

    fun onLogin(
        email: String,
        password: String,
        onLoginSuccess: () -> Unit
    ) {
        safeExecute(loginUseCase.execute(email, password))
            .safeSubscribe(
                onSuccess = { onLoginSuccess.invoke() }
            )
    }

}