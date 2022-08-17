package com.example.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM: BaseViewModel, B: ViewBinding>: Fragment() {

    protected lateinit var binding: B

    val viewModel: VM by this.getViewModel()

    abstract fun getBindingView(container: ViewGroup?, savedInstanceState: Bundle?) : B

    abstract fun getViewModel() : Lazy<VM>

    open fun receivedIntent(bundle: Bundle) { }

    open fun bindViews() { }

    open fun viewEvents() { }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getBindingView(container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle -> receivedIntent(bundle) }
        bindViews()
        viewEvents()
    }

}