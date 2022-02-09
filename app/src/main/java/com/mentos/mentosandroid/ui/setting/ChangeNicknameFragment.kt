package com.mentos.mentosandroid.ui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeNicknameBinding
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class ChangeNicknameFragment : Fragment() {
    private lateinit var binding: FragmentChangeNicknameBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

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
                    Log.d("별명 변경", "isSuccessNickName true")
                    Toast.makeText(requireContext(), "별명이 변경되었습니다!", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessNickName()
                    settingViewModel.setNickNameValid(false)
                }
                false -> {
                    Log.d("별명 변경", "isSuccessMajor false")
                    Toast.makeText(requireContext(), "별명 변경을 실패했습니다", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessNickName()
                    settingViewModel.setNickNameValid(false)
                }
            }
        }

    }
}