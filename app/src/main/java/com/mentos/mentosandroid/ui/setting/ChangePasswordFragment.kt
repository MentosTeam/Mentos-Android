package com.mentos.mentosandroid.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangePasswordBinding
import com.mentos.mentosandroid.ui.main.AuthActivity
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.makeToast
import com.mentos.mentosandroid.util.popBackStack

class ChangePasswordFragment : Fragment() {
    private lateinit var binding: FragmentChangePasswordBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

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
        settingViewModel.isSuccessChangePW.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    makeToast(requireContext(), R.string.toast_setting_password_success)
                    settingViewModel.initSuccesChangePW()
                    clearSDF()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                false -> {
                    makeToast(requireContext(), R.string.toast_setting_password_fail)
                    popBackStack()
                    settingViewModel.initSuccessNickName()
                    settingViewModel.setNickNameValid(false)
                }
            }
        }
    }

    private fun clearSDF() {
        SharedPreferenceController.clearNowState(requireContext())
        SharedPreferenceController.clearMyMentos(requireContext())
        SharedPreferenceController.clearOpenSex(requireContext())
        SharedPreferenceController.clearAuthData()
    }
}