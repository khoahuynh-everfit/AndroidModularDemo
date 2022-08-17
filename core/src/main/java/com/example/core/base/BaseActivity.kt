package com.example.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.component.popup.EverfitAlertDialog
import com.example.core.CoreApplication
import com.example.core.CoreNavigation
import com.example.component.R
import com.example.component.popup.LoadingViewDialog
import com.example.component.utils.observe
import com.example.component.utils.tryCatch
import com.example.data.model.error.EverfitError

abstract class BaseActivity<B: ViewBinding> : AppCompatActivity() {

    protected fun getNavigation(): CoreNavigation = application as CoreApplication

    protected lateinit var binding: B

    abstract fun getBindingView() : B

    abstract fun getMyViewModel() : BaseViewModel

    open fun receivedIntent(bundle: Bundle) { }

    open fun bindViews() { }

    open fun viewEvents() { }

    open fun observeViewModel() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getBindingView()
        setContentView(binding.root)
        intent?.extras?.let { bundle -> receivedIntent(bundle) }
        bindViews()
        viewEvents()
        observeViewModel()
        observe(getMyViewModel().errorLiveData, this::showErrorMessage)
        observe(getMyViewModel().loadingDialog, this::showLoadingDialog)
    }

    private var alertShowing = false
    private fun showErrorMessage(error: EverfitError) {
        if (!alertShowing) {
            alertShowing = true
            EverfitAlertDialog.newInstance(
                title = error.errorTitle,
                description = error.errorMessage,
                button = getString(R.string.btn_okay)
            ).show(supportFragmentManager, EverfitAlertDialog.javaClass.simpleName)
        }
    }

    private val loadingDialog: LoadingViewDialog by lazy {
        LoadingViewDialog().apply { isCancelable = false }
    }
    private fun showLoadingDialog(show: Boolean) {
        runOnUiThread {
            tryCatch {
                if (show) {
                    if (!loadingDialog.isVisible) {
                        loadingDialog.show(
                            supportFragmentManager,
                            LoadingViewDialog::class.java.simpleName
                        )
                    }
                } else {
                    loadingDialog.dismissAllowingStateLoss()
                }
            }
        }
    }

}