package com.mentos.mentosandroid.util.customdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.DialogMentosCategoryBinding

class MentosCategoryDialog(private val doAfterConfirm: (category: Int) -> Unit) :
    BottomSheetDialogFragment() {
    private lateinit var binding: DialogMentosCategoryBinding
    private lateinit var category: ArrayList<ConstraintLayout>
    private var selectedMentos: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogMentosCategoryBinding.inflate(inflater, container, false)
        category = arrayListOf(
            binding.mentosCategoryRedLayout,
            binding.mentosCategoryOrangeLayout,
            binding.mentosCategoryYellowLayout,
            binding.mentosCategoryGreenLayout,
            binding.mentosCategoryGreenDarkLayout,
            binding.mentosCategorySkyLayout,
            binding.mentosCategoryBlueLayout,
            binding.mentosCategoryPinkLayout,
            binding.mentosCategoryPurpleLayout,
            binding.mentosCategoryBrownLightLayout,
            binding.mentosCategoryBrownRedLayout,
            binding.mentosCategoryGrayLayout
        )
        setCompleteBtnBg()
        setMentosCategoryClickListener()
        setCompleteBtnClickListener()
        return binding.root
    }

    private fun setMentosCategoryClickListener() {
        for (i in 0 until category.size) {
            category[i].setOnClickListener {
                it.setBackgroundResource(R.drawable.shape_black_stroke_20)
                setGrayBg(i)
                selectedMentos = i
                setCompleteBtnBg()
            }
        }
    }

    private fun setGrayBg(index: Int) {
        for (i in 0 until index) {
            category[i].setBackgroundResource(R.drawable.shape_gray_stroke_20)
        }
        for (i in index + 1 until category.size) {
            category[i].setBackgroundResource(R.drawable.shape_gray_stroke_20)
        }
    }

    private fun setCompleteBtnBg() {
        if (selectedMentos == -1) {
            binding.mentosCategoryBtnComplete.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.mentosCategoryBtnComplete.isClickable = false
        } else {
            binding.mentosCategoryBtnComplete.setBackgroundResource(R.drawable.shape_black_fill_8)
            binding.mentosCategoryBtnComplete.isClickable = true
        }
    }

    private fun setCompleteBtnClickListener() {
        binding.mentosCategoryBtnComplete.setOnClickListener {
            doAfterConfirm(selectedMentos + 1)
            dialog?.dismiss()
        }
    }
}