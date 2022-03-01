package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeIntroBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.makeToast
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
            0 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
            }
            1 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentee)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentee)
            }
        }
        binding.settingIntroView.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setSuccessChangeMentosIntroObserve() {
        settingViewModel.isSuccessMentosIntro.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    makeToast(requireContext(), R.string.toast_setting_intro_success)
                    popBackStack()
                    popBackStack()
                    settingViewModel.initSuccessMentosIntro()
                    settingViewModel.clearCategory()
                }
                false -> {
                    makeToast(requireContext(), R.string.toast_setting_intro_fail)
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