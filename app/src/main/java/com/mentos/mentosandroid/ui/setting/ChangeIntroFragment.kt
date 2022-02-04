package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeIntroBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.popBackStack

class ChangeIntroFragment : Fragment() {
    private lateinit var binding: FragmentChangeIntroBinding
    private val settingViewModel by viewModels<SettingViewModel>()

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
        when (SharedPreferenceController.getNowState(requireContext())) {
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
                    //변경 성공 시 해야할 것 -> post
                    popBackStack() //일단 설정화면으로 돌아가게 함
                    popBackStack()
                }
                false -> {
                    //변경 실패 시 해야할 것
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