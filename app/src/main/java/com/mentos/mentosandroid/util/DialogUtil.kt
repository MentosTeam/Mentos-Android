package com.mentos.mentosandroid.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mentos.mentosandroid.databinding.DialogUtilBinding

class DialogUtil(private val message: Int, private val doAfterConfirm: () -> Unit) :
    DialogFragment() {
    private lateinit var binding: DialogUtilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUtilBinding.inflate(layoutInflater, container, false)
        setMessage()
        setClickListener()
        return binding.root
    }

    private fun setMessage() {
        binding.dialogUtilMessageTv.setText(message)
    }

    private fun setClickListener() {
        binding.dialogUtilConfirmTv.setOnClickListener {
            doAfterConfirm()
            dismiss()
        }

        binding.dialogUtilCancelTv.setOnClickListener {
            dismiss()
        }
    }
}