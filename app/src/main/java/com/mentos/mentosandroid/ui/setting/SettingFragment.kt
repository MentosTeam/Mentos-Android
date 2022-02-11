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
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.request.RequestChangePW
import com.mentos.mentosandroid.databinding.FragmentSettingBinding
import com.mentos.mentosandroid.ui.main.AuthActivity
import com.mentos.mentosandroid.util.*

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        settingViewModel.getSettingData()

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
            navigate(R.id.action_settingFragment_to_changeNicknameFragment)
        }
        binding.settingImageLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changeProfileImgFragment)
        }
        binding.settingMajorLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changeMajorFragment)
        }
        binding.settingMentosLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changeMentosFragment)
        }
        binding.settingPwLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }
        binding.settingPolicyLayout.setOnClickListener {
            //약관으로 이동
        }
        binding.settingLogoutTv.setOnClickListener {
            TwoButtonDialog(2) {
                clearSDF()
                startActivity(Intent(requireContext(), AuthActivity::class.java))
                requireActivity().finish()
                Toast.makeText(requireContext(), "로그아웃 되었습니다", Toast.LENGTH_SHORT).show()
            }.show(childFragmentManager, "logout")
        }
        binding.settingWithdrawalTv.setOnClickListener {
            TwoButtonDialog(1) {
                EditTextDialog(1) {
                    settingViewModel.postWithdrawal(password = it)
                    settingViewModel.isSuccessWithdrawal.observe(viewLifecycleOwner) { isSuccess ->
                        Log.d("탈퇴", isSuccess.toString())
                        if (isSuccess != null && isSuccess) {
                            OneButtonDialog(1) {
                                clearSDF()
                                startActivity(Intent(requireContext(), AuthActivity::class.java))
                                requireActivity().finish()
                                Toast.makeText(requireContext(), "회원탈퇴 되었습니다", Toast.LENGTH_SHORT).show()
                            }.show(childFragmentManager, "withdrawal")
                        }
                    }
                }.show(childFragmentManager, "withdrawal")
            }.show(childFragmentManager, "withdrawal")
        }
    }

    private fun clearSDF() {
        SharedPreferenceController.clearNowState(requireContext())
        SharedPreferenceController.clearMyMentos(requireContext())
        SharedPreferenceController.clearOpenSex(requireContext())
        SharedPreferenceController.clearAuthData()
    }
}