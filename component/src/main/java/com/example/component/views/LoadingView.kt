package com.example.component.views

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.model.KeyPath
import com.example.component.R

class LoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_view, this, true)
    }

    fun changeColorAsBranding(color: Int) {
        val animationView: LottieAnimationView = findViewById(R.id.loading_anim)
        animationView.addValueCallback(
            KeyPath("**"),
            LottieProperty.COLOR_FILTER,
            { PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP) }
        )
    }
}