package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeMajorBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class ChangeMajorFragment : Fragment() {
    private lateinit var binding: FragmentChangeMajorBinding
    private val settingViewModel by viewModels<SettingViewModel>()

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