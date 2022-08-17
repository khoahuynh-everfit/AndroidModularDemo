package com.example.component.utils

import android.util.Patterns

fun String.isEmail() : Boolean = Patterns.EMAIL_ADDRESS.matcher(this.trim()).matches()

inline fun tryCatch(crossinline func: () -> Unit) {
    try {
        func()
    } catch (ex: java.lang.Exception) {
        ex.printStackTrace()
    }
}