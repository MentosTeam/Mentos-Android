package com.mentos.mentosandroid.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.databinding.FragmentSettingBinding
import com.mentos.mentosandroid.ui.main.AuthActivity
import com.mentos.mentosandroid.util.*
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.customdialog.TwoButtonDialog

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
        setCurrentPush()
        setPushObserve()
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

    private fun setCurrentPush() {
        if (SharedPreferenceController.getNowState() == 0) {
            binding.switchMentorPush.visibility = View.VISIBLE
            binding.switchMenteePush.visibility = View.GONE

            settingViewModel.mentorAgreementPush.value =
                SharedPreferenceController.getAgreementPush(0)
        } else {
            binding.switchMentorPush.visibility = View.GONE
            binding.switchMenteePush.visibility = View.VISIBLE

            settingViewModel.menteeAgreementPush.value =
                SharedPreferenceController.getAgreementPush(1)
        }
    }

    private fun setPushObserve() {
        if (SharedPreferenceController.getNowState() == 0) {
            settingViewModel.mentorAgreementPush.observe(viewLifecycleOwner) { isAgree ->
                SharedPreferenceController.setAgreementPush(0, isAgree)
            }
        } else {
            settingViewModel.mentorAgreementPush.observe(viewLifecycleOwner) { isAgree ->
                SharedPreferenceController.setAgreementPush(1, isAgree)
            }
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
                settingViewModel.deleteDeviceFcmToken(SharedPreferenceController.getDeviceFcmToken())
                settingViewModel.isSuccessDeleteToken.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess) {
                        clearSDF()
                        startActivity(Intent(requireContext(), AuthActivity::class.java))
                        requireActivity().finish()
                        makeToast(requireContext(), R.string.toast_logout)
                    }
                }
            }.show(childFragmentManager, "logout")
        }
        binding.settingWithdrawalTv.setOnClickListener {
            TwoButtonDialog(1) {
                EditTextDialog(1) {
                    settingViewModel.postWithdrawal(password = it)
                    settingViewModel.isSuccessWithdrawal.observe(viewLifecycleOwner) { isSuccess ->
                        if (isSuccess != null && isSuccess) {
                            OneButtonDialog(1) {
                                clearSDF()
                                startActivity(Intent(requireContext(), AuthActivity::class.java))
                                requireActivity().finish()
                                makeToast(requireContext(), R.string.toast_withdrawal)
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