package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileMenteeBinding
import com.mentos.mentosandroid.util.SharedPreferenceController

class ProfileMenteeFragment : Fragment() {
    private lateinit var binding: FragmentProfileMenteeBinding
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileMenteeBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()
        initSex()
        initImg()

        return binding.root
    }

    private fun initSex() {
        val isOpen = SharedPreferenceController.getOpenSex(requireContext())
        if (isOpen) {
            binding.menteeProfileOpenSexLayout.visibility = View.VISIBLE
            binding.menteeProfilePrivateSexLayout.visibility = View.GONE
        } else {
            binding.menteeProfileOpenSexLayout.visibility = View.GONE
            binding.menteeProfilePrivateSexLayout.visibility = View.VISIBLE
        }
    }

    private fun initViewModel() {
        //뷰모델 연결
        binding.profileViewModel = profileViewModel
        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this
    }

    private fun initImg() {
        profileViewModel.menteeProfileData.observe(viewLifecycleOwner) { menteeProfileData ->
            if (menteeProfileData == null) {
                binding.menteeProfileImg.setImageResource(R.drawable.img_home_user)
            } else {
                if (menteeProfileData.basicInformation.profileImage == null) {
                    binding.menteeProfileImg.setImageResource(R.drawable.img_home_user)
                } else {
                    Glide.with(requireContext())
                        .load(menteeProfileData.basicInformation.profileImage)
                        .into(binding.menteeProfileImg)
                }
            }
        }
    }
}