package com.example.modular_splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.component.constants.ClientConstants
import com.example.core.base.BaseViewModel
import com.example.data.model.authentication.User
import com.example.data.usecase.authentication.CheckLocalTokenUseCase
import com.example.data.usecase.authentication.GetProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkLocalTokenUseCase: CheckLocalTokenUseCase,
    private val getProfileUseCase: GetProfileUseCase,
) : BaseViewModel() {
    companion object {
        const val LOGIN_SCREEN = "login_screen"
        const val MAIN_SCREEN = "main_screen"
        const val WAITING_ACTIVATION = "waiting_activation"
    }
    private val _splashRouteData = MutableLiveData<String>()
    val splashRouteData : LiveData<String>
        get() = _splashRouteData

    private val _userData: MutableLiveData<User?> = MutableLiveData(null)
    val userData: LiveData<User?>
        get() = _userData

    fun bound() {
        safeExecute(checkLocalTokenUseCase.execute())
            .safeSubscribe(
                onSuccess = { valid ->
                    when {
                        valid -> requestProfile()
                        else -> _splashRouteData.postValue(LOGIN_SCREEN)
                    }
                }, onError = {
                    _splashRouteData.postValue(LOGIN_SCREEN)
                }
            )
    }

    private fun requestProfile() {
        safeExecute(getProfileUseCase.execute())
            .safeSubscribe(
                onSuccess = { user ->
                    _userData.postValue(user)
                }, onError = {
                    checkNavigationScreen()
                }
            )
    }

    fun checkNavigationScreen() {
        when (_userData.value?.clientConnection) {
            ClientConstants.ClientConnection.WAITING_ACTIVATION.value -> {
                _splashRouteData.postValue(WAITING_ACTIVATION)
            }
            else -> _splashRouteData.postValue(MAIN_SCREEN)
        }
    }

}