package com.example.component.utils

import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.onClick(allowFastClick: Boolean = false, onClicked: (View) -> Unit) {
    setOnClickListener {
        if (allowFastClick) {
            onClicked(it)
        } else {
            if (!isClickTooFast()) {
                onClicked(it)
            }
        }
    }
}

private var mLastClickTime = 0L
private var mViewClickId: Int = -1
private fun View.isClickTooFast(): Boolean {
    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000 && mViewClickId == this.id) {
        return true
    }
    mViewClickId = this.id
    mLastClickTime = SystemClock.elapsedRealtime()
    return false
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.show(show: Boolean) {
    this.visibility = if (show) View.VISIBLE else View.GONE
}

/**
 * Allow implementation of [TextWatcher] into [EditText] without unused method call
 */
fun EditText.afterTextChange(afterTextChange: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            afterTextChange.invoke(p0?.toString() ?: "")
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    })
}