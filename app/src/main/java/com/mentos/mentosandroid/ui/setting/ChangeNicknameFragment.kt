package com.mentos.mentosandroid.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeNicknameBinding
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class ChangeNicknameFragment : Fragment() {
    private lateinit var binding: FragmentChangeNicknameBinding
    private val settingViewModel by viewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeNicknameBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBtnBackClickListener()
        setNickNameCheckClickListener()
        setNickNameObserve()
        setSuccessNickNameObserve()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.changeNicknameBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setNickNameCheckClickListener() {
        binding.changeNicknameCheckTv.setOnClickListener {
            settingViewModel.getNickNameValid()
            setNickNameValidObserve()
        }
    }

    private fun setNickNameValidObserve() {
        // nickName 중복 api를 호출 했을때만 이 함수 호출
        settingViewModel.isNickNameValid.observe(viewLifecycleOwner) { isNickNameValid ->
            when (isNickNameValid) {
                true -> {
                    binding.changeNickNameSuccessTv.visibility = View.VISIBLE
                    binding.changeNickNameFailTv.visibility = View.GONE
                }
                false -> {
                    binding.changeNickNameSuccessTv.visibility = View.GONE
                    binding.changeNickNameFailTv.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setNickNameObserve() {
        settingViewModel.newNickName.observe(viewLifecycleOwner) { newNickName ->
            if (newNickName.isNullOrEmpty()) {
                settingViewModel.setNickNameValid(false)
            }
        }
    }

    //등록
    private fun setSuccessNickNameObserve() {
        settingViewModel.isSuccessNickName.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    //+ post
                    popBackStack()
                }
                false -> {
                    //오류 알려줘야함
//                    DialogUtil(0) {}.show(childFragmentManager, "sign_in_fail")
                }
            }
        }

    }
}