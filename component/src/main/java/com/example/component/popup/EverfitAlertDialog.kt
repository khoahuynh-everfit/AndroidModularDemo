package com.example.component.popup

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.component.R
import com.example.component.databinding.FragmentEverfitAlertDialogBinding
import com.example.component.utils.gone
import com.example.component.utils.visible

class EverfitAlertDialog : DialogFragment() {

    companion object {
        private val CONTENT_ICON = "CONTENT_ICON"
        private val CONTENT_TITLE = "CONTENT_TITLE"
        private val CONTENT_DESCRIPTION = "CONTENT_DESCRIPTION"
        private val CONTENT_BUTTON = "CONTENT_BUTTON"

        fun newInstance(
            icon: Int = R.drawable.ic_warning,
            title: String = "",
            description: String = "",
            button: String = "",
            onOkClick: () -> Unit = {},
            onDismiss: () -> Unit = {},
        ) = EverfitAlertDialog().apply {
            arguments = bundleOf(
                CONTENT_ICON to icon,
                CONTENT_TITLE to title,
                CONTENT_DESCRIPTION to description,
                CONTENT_BUTTON to button,
            )
            this.onOkClick = onOkClick
            this.onDismiss = onDismiss
        }

    }

    protected lateinit var binding: FragmentEverfitAlertDialogBinding
    private var onOkClick: (() -> Unit)? = null
    private var onDismiss: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentEverfitAlertDialogBinding.inflate(layoutInflater, null, false)
        val builder = AlertDialog.Builder(requireActivity(), R.style.RoundedDialog).apply { setView(binding.root) }
        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(CONTENT_ICON)?.let { iconRes ->
            binding.ivIcon.visible()
            binding.ivIcon.setImageResource(iconRes)
        }?: run {
            binding.ivIcon.gone()
        }
        binding.tvTitle.text = arguments?.getString(CONTENT_TITLE) ?: ""
        binding.tvDescription.text = arguments?.getString(CONTENT_DESCRIPTION) ?: ""
        binding.btnOk.text = arguments?.getString(CONTENT_BUTTON) ?: ""
    }

}