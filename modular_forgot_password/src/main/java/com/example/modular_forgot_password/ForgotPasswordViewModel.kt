package com.example.modular_forgot_password

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.base.BaseViewModel
import com.example.data.usecase.authentication.ForgotPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase
) : BaseViewModel() {

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = _errorMessage

    fun forgotPassword(email: String, onComplete: () -> Unit) {
        safeExecute(forgotPasswordUseCase.execute(email))
            .safeSubscribe(
                onSuccess = {
                    onComplete()
                },
                onError = { error ->
                    _errorMessage.postValue(error.errorMessage)
                }
            )
    }


}