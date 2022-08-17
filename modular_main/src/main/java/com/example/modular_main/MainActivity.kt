package com.example.modular_main

import androidx.activity.viewModels
import com.example.core.base.BaseActivity
import com.example.modular_main.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun getBindingView(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun getMyViewModel() = viewModel

    override fun viewEvents() {
        super.viewEvents()
        binding.btnLogout.setOnClickListener {
            viewModel.onLogout {
                (getNavigation() as? ModularMainNavigation)?.onLogout(this) ?: run {
                    throw IllegalStateException("application should implement ModularMainNavigation")
                }
            }
        }
    }
}