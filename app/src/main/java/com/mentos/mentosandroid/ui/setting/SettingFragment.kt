package com.mentos.mentosandroid.ui.setting

import android.content.Intent
import android.net.Uri
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
        when (SharedPreferenceController.getOpenSex()) {
            1 -> binding.switchSex.isChecked = true
            else -> binding.switchSex.isChecked = false
        }
    }

    private fun setOpenSexObserve() {
        binding.switchSex.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.setOpenSex()
            if(isChecked) {
                SharedPreferenceController.setOpenSex(1)
            } else {
                SharedPreferenceController.setOpenSex(0)
            }
        }
    }

    private fun setCurrentPush() {
        when (SharedPreferenceController.getAgreementPush()) {
            1 -> binding.switchPush.isChecked = true
            else -> binding.switchPush.isChecked = false
        }
    }

    private fun setPushObserve() {
        binding.switchPush.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.setSendNotification()
            if(isChecked) {
                SharedPreferenceController.setAgreementPush(1)
            } else {
                SharedPreferenceController.setAgreementPush(0)
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
        binding.settingNoticeLayout.setOnClickListener {
            navigateWithData(
                SettingFragmentDirections.actionSettingFragmentToNotificationFragment(
                    from = "setting"
                )
            )
        }
        binding.settingPwLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }
        binding.settingTermsPersonalLayout.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.link_terms_personal))
                )
            )
        }
        binding.settingTermsServiceLayout.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.link_terms_service))
                )
            )
        }
        binding.settingLogoutTv.setOnClickListener {
            TwoButtonDialog(2) {
                settingViewModel.deleteDeviceFcmToken(SharedPreferenceController.getDeviceFcmToken())
                settingViewModel.isSuccessDeleteToken.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess) {
                        SharedPreferenceController.setSdfAllClear(requireContext())
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
                                SharedPreferenceController.setSdfAllClear(requireContext())
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
}