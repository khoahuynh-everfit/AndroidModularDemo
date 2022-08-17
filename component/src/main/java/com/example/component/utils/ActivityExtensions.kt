package com.example.component.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.component.R
import java.lang.Exception

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    try {
        liveData.observe(this) { data ->
            data?.let { t -> action(t) }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent = Intent(context, T::class.java)

inline fun Activity.overridePendingTransitionEnter() { overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left) }

inline fun <reified T : Any> Activity.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    this.startActivity(intent, options)
    overridePendingTransitionEnter()
}

inline fun <reified T : Any> Activity.launchActivityForResult(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    this.startActivityForResult(intent, requestCode, options)
    overridePendingTransitionEnter()
}