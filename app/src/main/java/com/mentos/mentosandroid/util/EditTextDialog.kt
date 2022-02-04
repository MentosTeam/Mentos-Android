package com.mentos.mentosandroid.util

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.DialogEditTextBinding

class EditTextDialog(
    private val dialogMode: Int,
    private val doAfterConfirm: (getText: String) -> Unit
) :
    DialogFragment() {
    private lateinit var binding: DialogEditTextBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogEditTextBinding.inflate(layoutInflater, container, false)
        setLayout()
        initView()
        setClickListener()
        return binding.root
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setBackgroundDrawableResource(R.drawable.shape_gray_stroke_20)
            }
        }
    }

    private fun initView() {
        with(binding) {
            when (dialogMode) {
                REVIEW_TEXT -> {
                    dialogEtTitleTv.setText(R.string.dialog_review_et_title)
                    dialogEtSubTitleTv.setText(R.string.dialog_review_et_sub_title)
                    dialogEtSubTitleTv.visibility = View.VISIBLE
                    dialogEtPasswordLayout.visibility = View.GONE
                    dialogEtReviewLayout.visibility = View.VISIBLE
                    dialogBtnComplete.setText(R.string.dialog_review_et_btn)
                    dialogPasswordFailTv.visibility = View.GONE
                    dialogEtReviewEt.setEditTextChangeListener()
                }
                WITHDRAWAL -> {
                    dialogEtTitleTv.setText(R.string.dialog_withdrawal_password)
                    dialogEtSubTitleTv.visibility = View.GONE
                    dialogEtPasswordLayout.visibility = View.VISIBLE
                    dialogEtReviewLayout.visibility = View.GONE
                    dialogBtnComplete.setText(R.string.dialog_withdrawal_password_btn)
                    dialogEtPasswordEt.setEditTextChangeListener()
                }
                else -> throw IllegalStateException()
            }
        }
    }

    private fun EditText.setEditTextChangeListener() {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                chkTextValidation(count)
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun chkTextValidation(count: Int) {
        if (count == 0) {
            binding.dialogBtnComplete.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.dialogBtnComplete.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.gray_c7c6
                )
            )
            binding.dialogBtnComplete.isClickable = false
        } else {
            binding.dialogBtnComplete.setBackgroundResource(R.drawable.shape_black_fill_8)
            binding.dialogBtnComplete.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white
                )
            )
            binding.dialogBtnComplete.isClickable = true
        }
    }

    private fun setClickListener() {
        binding.dialogBtnComplete.setOnClickListener {
            when (dialogMode) {
                REVIEW_TEXT -> {
                    doAfterConfirm(binding.dialogEtReviewEt.text.toString())
                }
                WITHDRAWAL -> {
                    // 서버연결
                    // 비밀번호 불일치 시
                    // binding.dialogPasswordFailTv.visibility = View.VISIBLE
                    doAfterConfirm(binding.dialogEtPasswordEt.text.toString())
                }
            }
            dismiss()
        }
    }

    companion object {
        const val REVIEW_TEXT = 0
        const val WITHDRAWAL = 1
    }
}