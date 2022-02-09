package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.databinding.FragmentChangeMajorBinding
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
                    Log.d("전공 변경", "isSuccessMajor true")
                    Toast.makeText(requireContext(), "전공이 변경되었습니다!", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessMajor()
                }
                false -> {
                    Log.d("전공 변경", "isSuccessMajor false")
                    Toast.makeText(requireContext(), "전공 변경을 실패했습니다", Toast.LENGTH_SHORT).show()
                    popBackStack()
                    settingViewModel.initSuccessMajor()
                }
            }
        }

    }
}