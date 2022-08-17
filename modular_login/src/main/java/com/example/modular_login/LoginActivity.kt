package com.example.modular_login

import androidx.activity.viewModels
import com.example.component.utils.onClick
import com.example.core.base.BaseActivity
import com.example.modular_login.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()

    override fun getBindingView(): ActivityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)

    override fun getMyViewModel() = viewModel

    override fun viewEvents() {
        super.viewEvents()
        binding.btnLogin.onClick {
            viewModel.onLogin(
                email = binding.edtEmail.text.toString(),
                password = binding.edtPassword.text.toString()
            ) {
                getLoginNavigation().navigateLoginToMain(this)
            }
        }

        binding.txtForgotPassword.onClick {
            getLoginNavigation().navigateLoginToForgotPassword(this)
        }
    }

    private fun getLoginNavigation(): ModularLoginNavigation {
        val navigation = getNavigation()
        if (navigation !is ModularLoginNavigation)
            throw IllegalStateException("application should implement ModularSplashNavigation")
        return navigation
    }

}