package com.mentos.mentosandroid.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.DialogTwoButtonBinding

class TwoButtonDialog(
    private val dialogMode: Int,
    private val doAfterConfirm: () -> Unit
) :
    DialogFragment() {
    private lateinit var binding: DialogTwoButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogTwoButtonBinding.inflate(layoutInflater, container, false)
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
                RECORD -> {
                    dialogRecordTitleTv.visibility = View.VISIBLE
                    dialogSubTitleTv.visibility = View.VISIBLE
                    dialogWithdrawalTitleTv.visibility = View.GONE
                    dialogWithdrawalBtnLayout.visibility = View.GONE
                    dialogRecordBtnLayout.visibility = View.VISIBLE
                }
                WITHDRAWAL -> {
                    dialogRecordTitleTv.visibility = View.GONE
                    dialogSubTitleTv.visibility = View.GONE
                    dialogWithdrawalTitleTv.visibility = View.VISIBLE
                    dialogWithdrawalBtnLayout.visibility = View.VISIBLE
                    dialogRecordBtnLayout.visibility = View.GONE
                }
                else -> throw IllegalStateException()
            }
        }
    }

    private fun setClickListener() {
        binding.dialogRecordFirstBtnTv.setOnClickListener {
            doAfterConfirm()
            dismiss()
        }

        binding.dialogWithdrawalFirstBtnTv.setOnClickListener {
            doAfterConfirm()
            dismiss()
        }

        binding.dialogRecordSecondBtnTv.setOnClickListener {
            dismiss()
        }

        binding.dialogWithdrawalSecondBtnTv.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        const val RECORD = 0
        const val WITHDRAWAL = 1
    }
}