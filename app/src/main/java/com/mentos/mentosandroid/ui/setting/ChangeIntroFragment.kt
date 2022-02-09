package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeIntroBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.popBackStack

class ChangeIntroFragment : Fragment() {
    private lateinit var binding: FragmentChangeIntroBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeIntroBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initView()
        setSuccessChangeMentosIntroObserve()
        setBtnBackClickListener()
        return binding.root
    }

    private fun initView() {
        when (SharedPreferenceController.getNowState()) {
            //멘토
            0 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
            }
            //멘티
            1 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentee)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentee)
            }
        }
    }

    private fun setSuccessChangeMentosIntroObserve() {
        settingViewModel.isSuccessMentosIntro.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    Log.d("멘토스, 소개 변경", "isSuccessMentosIntro true")
                    Toast.makeText(requireContext(), "멘토-쓰 및 자기소개가 변경되었습니다!", Toast.LENGTH_SHORT)
                        .show()
                    popBackStack()
                    popBackStack()
                    settingViewModel.initSuccessMentosIntro()
                    settingViewModel.clearCategory()
                    Log.d("멘토스 소개 변경", settingViewModel.tempCategory.toString())
                    Log.d("멘토스 소개 변경", settingViewModel.selectedCategory.value.toString())
//                    settingViewModel.setTempCategory()
                }
                false -> {
                    Log.d("멘토스, 소개 변경", "isSuccessMentosIntro false")
                    Toast.makeText(requireContext(), "멘토-쓰 및 자기소개 변경을 실패했습니다", Toast.LENGTH_SHORT)
                        .show()
                    popBackStack()
                    popBackStack()
                    settingViewModel.initSuccessMentosIntro()
                    settingViewModel.clearCategory()
                }
            }
        }
    }

    private fun setBtnBackClickListener() {
        binding.changeIntroBackIb.setOnClickListener {
            popBackStack()
        }
    }

}