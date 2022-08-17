package com.example.modular_forgot_password

import androidx.activity.viewModels
import com.example.component.utils.*
import com.example.core.base.BaseActivity
import com.example.modular_forgot_password.databinding.ActivityForgotPasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordActivity : BaseActivity<ActivityForgotPasswordBinding>() {

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun getBindingView(): ActivityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(layoutInflater)

    override fun getMyViewModel() = viewModel

    override fun observeViewModel() {
        super.observeViewModel()
        observe(viewModel.errorMessage) { errorMessage ->
            binding.txtForgotPasswordError.visible()
            binding.txtForgotPasswordError.text = errorMessage
        }
    }

    override fun viewEvents() {
        super.viewEvents()
        binding.imvBack.onClick {
            finish()
        }

        binding.btnSend.onClick {
            val email = binding.edtForgotPasswordEmail.text.toString().trim()

            when {
                email.isEmpty() -> {
                    binding.txtForgotPasswordError.text = getString(com.example.component.R.string.email_is_empty)
                    binding.txtForgotPasswordError.visible()
                }
                !email.isEmail() -> {
                    binding.txtForgotPasswordError.text = getString(com.example.component.R.string.email_has_wrong_format)
                    binding.txtForgotPasswordError.visible()
                }
                else -> {
                    viewModel.forgotPassword(email){
                        setResult(RESULT_OK)
                        finish()
                    }
                }
            }
        }
        binding.edtForgotPasswordEmail.afterTextChange {
            binding.txtForgotPasswordError.gone()
        }
    }

}