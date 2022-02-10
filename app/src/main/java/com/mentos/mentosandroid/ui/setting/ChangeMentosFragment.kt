package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeMentosBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class ChangeMentosFragment : Fragment() {
    private lateinit var binding: FragmentChangeMentosBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()
    private lateinit var category: ArrayList<CheckBox>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeMentosBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initCategoryArray()
        setBtnCategoryClickListener()
        setCategoryObserve()
        setBtnCompleteClickListener()
        initView()
        setBtnBackClickListener()

        initClickedCategory()
        return binding.root
    }

    private fun initCategoryArray() {
        category = arrayListOf(
            binding.mentosRedCb,
            binding.mentosOrangeCb,
            binding.mentosYellowCb,
            binding.mentosGreenCb,
            binding.mentosGreenDarkCb,
            binding.mentosSkyCb,
            binding.mentosBlueCb,
            binding.mentosPinkCb,
            binding.mentosPurpleCb,
            binding.mentosBrownLightCb,
            binding.mentosBrownRedCb,
            binding.mentosGrayCb
        )
    }

    private fun initClickedCategory() {
        settingViewModel.tempCategory.forEach { categoryIdx ->
            category[categoryIdx - 1].isChecked = true
        }
        settingViewModel.setTempCategory()
    }

    private fun setBtnCategoryClickListener() {
        for (i in 0 until category.size) {
            category[i].setCheckBoxClickListener(i + 1)
        }
    }

    private fun setCategoryObserve() {
        settingViewModel.selectedCategory.observe(viewLifecycleOwner) { categoryList ->
            when (categoryList.size) {
                2 -> {
                    for (i in 0 until category.size) {
                        category[i].setCategoryClickUnable()
                    }
                }
                else -> {
                    for (i in 0 until category.size) {
                        category[i].isClickable = true
                    }
                }
            }
        }
    }

    private fun CheckBox.setCheckBoxClickListener(category: Int) {
        this.setOnClickListener {
            if (this.isChecked) {
                settingViewModel.setCategory(category)
            } else {
                settingViewModel.removeCategory(category)
            }
        }
    }

    private fun CheckBox.setCategoryClickUnable() {
        if (!this.isChecked) {
            this.isClickable = false
        }
    }

    private fun initView() {
        when (SharedPreferenceController.getNowState()) {
            //멘토
            0 -> {
                binding.changeMentosTitleTv.setText(R.string.account_intro_title_mentor)
                binding.changeMentosSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
            }
            //멘티
            1 -> {
                binding.changeMentosTitleTv.setText(R.string.account_intro_title_mentee)
                binding.changeMentosSubTitleTv.setText(R.string.account_intro_sub_title_mentee)
            }
        }
    }

    private fun setBtnCompleteClickListener() {
        binding.changeMentosBtnComplete.setOnClickListener {
            navigate(R.id.action_changeMentosFragment_to_changeIntroFragment)
        }
    }

    private fun setBtnBackClickListener() {
        binding.changeMentosBackIb.setOnClickListener {
            popBackStack()
        }
    }

}