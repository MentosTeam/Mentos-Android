package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSettingBinding
import com.mentos.mentosandroid.util.navigate

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSettingBinding.inflate(inflater, container, false)

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
        binding.settingPolicyLayout.setOnClickListener {

        }
        binding.settingPwLayout.setOnClickListener {
            navigate(R.id.action_settingFragment_to_changePasswordFragment)
        }

        return binding.root
    }
}