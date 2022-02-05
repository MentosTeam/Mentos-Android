package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSettingBinding
import com.mentos.mentosandroid.util.*

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val settingViewModel by viewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBtnClickListener()
        setCurrentOpenSex()
        setOpenSexObserve()
        return binding.root
    }

    private fun setCurrentOpenSex() {
        settingViewModel.openSex.value = SharedPreferenceController.getOpenSex(requireContext())
    }

    private fun setOpenSexObserve() {
        settingViewModel.openSex.observe(viewLifecycleOwner) { isOpen ->
            SharedPreferenceController.setOpenSex(requireContext(), isOpen)
        }
    }

    private fun setBtnClickListener() {
        binding.settingNicknameLayout.setOnClickListener {
            //기존 닉네임 전달 필요
            navigate(R.id.action_settingFragment_to_changeNicknameFragment)
        }
        binding.settingImageLayout.setOnClickListener {
            //기존 이미지 전달 필요
            navigate(R.id.action_settingFragment_to_changeProfileImgFragment)
        }
        binding.settingMajorLayout.setOnClickListener {
            //기존 전공, 학번 전달 필요
            navigate(R.id.action_settingFragment_to_changeMajorFragment)
        }
        binding.settingMentosLayout.setOnClickListener {
            //기존 멘토스, 자기소개 전달 필요
            navigate(R.id.action_settingFragment_to_changeMentosFragment)
        }
        binding.settingPwLayout.setOnClickListener {
            //기존 비밀번호 전달 필요?
            navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }
        binding.settingPolicyLayout.setOnClickListener {
            //약관으로 이동
        }
        binding.settingWithdrawalTv.setOnClickListener {
            TwoButtonDialog(1) {
                EditTextDialog(1) {
                    OneButtonDialog(1) {
                        //탈퇴후 처리
                    }.show(childFragmentManager, "withdrawal")
                }.show(childFragmentManager, "withdrawal")
            }.show(childFragmentManager, "withdrawal")
        }
    }
}