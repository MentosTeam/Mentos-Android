package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeMajorBinding
import com.mentos.mentosandroid.util.makeToast
import com.mentos.mentosandroid.util.popBackStack

class ChangeMajorFragment : Fragment() {
    private lateinit var binding: FragmentChangeMajorBinding
    private val settingViewModel by activityViewModels<SettingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeMajorBinding.inflate(inflater, container, false)
        binding.settingViewModel = settingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setBtnBackClickListener()
        setSuccessMajorObserve()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.changeMajorBackIb.setOnClickListener {
            popBackStack()
        }
    }

    //등록
    private fun setSuccessMajorObserve() {
        settingViewModel.isSuccessMajor.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    makeToast(requireContext(), R.string.toast_setting_major_success)
                    popBackStack()
                    settingViewModel.initSuccessMajor()
                }
                false -> {
                    makeToast(requireContext(), R.string.toast_setting_major_fail)
                    popBackStack()
                    settingViewModel.initSuccessMajor()
                }
            }
        }

    }
}