package com.example.modular_main

import com.example.core.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): BaseViewModel() {

    fun onLogout(onLogoutComplete: () -> Unit) {
        onLogoutComplete.invoke()
    }

}