package com.example.component.utils

import android.view.View
import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.init(initialValue: T) = apply { setValue(initialValue) }