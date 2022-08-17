package com.example.modular_splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import com.example.component.R
import com.example.component.utils.observe
import com.example.core.base.BaseActivity
import com.example.data.model.authentication.User
import com.example.modular_splash.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val viewModel : SplashViewModel by viewModels()

    override fun getBindingView(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun getMyViewModel() = viewModel

    override fun bindViews() {
        super.bindViews()
        viewModel.bound()
    }

    override fun observeViewModel() {
        super.observeViewModel()
        observe(viewModel.userData, this::handleUserData)
        observe(viewModel.splashRouteData, this::checkNavigationScreen)
    }

    private fun handleUserData(user: User?) {
        user?.let { user ->
            binding.labelWelcome.text = getString(R.string.label_hello_welcome_back, user.firstName)
        }
        setUpAnimation()
    }

    private fun setUpAnimation() {
        val logoKeyFrame = ObjectAnimator.ofFloat(binding.logo, View.TRANSLATION_Y, -100f)
        val textKeyFrame = ObjectAnimator.ofFloat(binding.labelWelcome, View.TRANSLATION_Y, -100f)
        val texAlphaKeyFrame = ObjectAnimator.ofFloat(binding.labelWelcome, View.ALPHA, 1f)

        AnimatorSet().apply {
            duration = 1000
            startDelay = 200
            playTogether(logoKeyFrame, textKeyFrame, texAlphaKeyFrame)
            doOnEnd {
                viewModel.checkNavigationScreen()
            }
            start()
        }
    }

    private fun checkNavigationScreen(route: String) {
        when (route) {
            SplashViewModel.MAIN_SCREEN -> {
                getSplashNavigation().navigateSplashToMain(this)
            }
            SplashViewModel.WAITING_ACTIVATION -> {
                getSplashNavigation().navigateSplashToWaitingActivation(this)
            }
            else -> {
                getSplashNavigation().navigateSplashToLogin(this)
            }
        }
    }

    private fun getSplashNavigation(): ModularSplashNavigation {
        val navigation = getNavigation()
        if (navigation !is ModularSplashNavigation)
            throw IllegalStateException("application should implement ModularSplashNavigation")
        return navigation
    }

}