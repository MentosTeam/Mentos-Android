package com.mentos.mentosandroid.ui.setting

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangePasswordBinding
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.popBackStack

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private val settingViewModel by viewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBtnBackClickListener()
        setSuccessChangePasswordObserve()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.changePwBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setSuccessChangePasswordObserve() {
        val currentPW = "mentos123!"
        settingViewModel.currentPassword = currentPW
        settingViewModel.isSuccessChangePW.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                   //변경 성공 시 해야할 것 -> post, 로그아웃, 로그인 참
                    popBackStack() //일단 설정화면으로 돌아가게 함
                }
                false -> {
                    //변경 실패 시 해야할 것
                }
            }
        }
    }
}