package com.mentos.mentosandroid.ui.setting

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.databinding.FragmentChangePasswordBinding
import com.mentos.mentosandroid.ui.main.AuthActivity
import com.mentos.mentosandroid.util.SharedPreferenceController
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
                    Log.d("비번 변경", "isSuccessChangePW true")
                    Toast.makeText(requireContext(), "비밀번호가 변경되었습니다!", Toast.LENGTH_SHORT).show()
                    settingViewModel.initSuccesChangePW()

                    clearSDF()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                false -> {
                    Log.d("비번 변경", "isSuccessChangePW false")
                    Toast.makeText(requireContext(), "비밀번호 변경을 실패했습니다", Toast.LENGTH_SHORT).show()
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