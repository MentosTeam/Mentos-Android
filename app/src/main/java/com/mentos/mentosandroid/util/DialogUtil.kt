package com.mentos.mentosandroid.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.DialogUtilBinding

class DialogUtil(private val dialogMode: Int, private val doAfterConfirm: () -> Unit) :
    DialogFragment() {
    private lateinit var binding: DialogUtilBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogUtilBinding.inflate(layoutInflater, container, false)
        setMessage()
        setCancelVisibility()
        setClickListener()
        return binding.root
    }

    private fun setMessage() {
        binding.dialogUtilMessageTv.text = when (dialogMode) {
            SIGN_IN_FAIL -> getString(R.string.dialog_sign_in_fail)
            SCHOOL_FAIL -> getString(R.string.dialog_school_fail)
            EMAIL_CONFIRM_FAIL -> getString(R.string.dialog_email_confirm_fail)
            FIND_PASSWORD -> getString(R.string.dialog_find_password)
            POST_DELETE -> getString(R.string.dialog_post_delete)
            STOP_WRITE -> getString(R.string.dialog_stop_write)
            FIND_PASSWORD_FAIL -> getString(R.string.dialog_find_password_fail)
            else -> throw IllegalStateException()
        }
    }

    private fun setCancelVisibility() {
        binding.dialogUtilCancelTv.visibility = when (dialogMode) {
            SIGN_IN_FAIL, SCHOOL_FAIL, EMAIL_CONFIRM_FAIL, FIND_PASSWORD, FIND_PASSWORD_FAIL -> View.GONE
            else -> View.VISIBLE
        }
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

    companion object {
        const val SIGN_IN_FAIL = 0
        const val SCHOOL_FAIL = 1
        const val EMAIL_CONFIRM_FAIL = 2
        const val FIND_PASSWORD = 3
        const val POST_DELETE = 4
        const val STOP_WRITE = 5
        const val FIND_PASSWORD_FAIL = 6
    }
}