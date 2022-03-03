package com.mentos.mentosandroid.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileMenteeBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.navigateWithData

class ProfileMenteeFragment : Fragment() {
    private lateinit var binding: FragmentProfileMenteeBinding
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileMenteeBinding.inflate(inflater, container, false)

        initViewModel()
        initSex()
        initImg()
        setFeedbackLayoutClickListener()
        initCreateMentorView()
        setCreateMentorClickListener()

        return binding.root
    }

    private fun setCreateMentorClickListener() {
        binding.menteeProfileAddMentorLayout.setOnClickListener {
            navigateWithData(
                ProfileFragmentDirections.actionProfileFragmentToOtherAccountMentosFragment(
                    1
                )
            )
        }
    }

    private fun setFeedbackLayoutClickListener() {
        binding.menteeProfileInformLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.mentos_gmail)))
            startActivity(intent)
        }
    }

    private fun initCreateMentorView() {
        profileViewModel.profileState.observe(viewLifecycleOwner) { profileState ->
            when (profileState) {
                2 -> {
                    binding.menteeProfileAddMentorLayout.visibility = View.VISIBLE
                }
                3 -> {
                    binding.menteeProfileAddMentorLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun initSex() {
        when (SharedPreferenceController.getOpenSex()) {
            1 -> {
                binding.menteeProfileOpenSexLayout.visibility = View.VISIBLE
                binding.menteeProfilePrivateSexLayout.visibility = View.GONE
            }
            else -> {
                binding.menteeProfileOpenSexLayout.visibility = View.GONE
                binding.menteeProfilePrivateSexLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun initViewModel() {
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun initImg() {
        profileViewModel.menteeProfileData.observe(viewLifecycleOwner) { menteeProfileData ->
            if (menteeProfileData == null) {
                binding.menteeProfileImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                if (menteeProfileData.basicInformation.profileImage == null) {
                    binding.menteeProfileImg.setImageResource(R.drawable.img_default_mentos)
                } else {
                    Glide.with(requireContext())
                        .load(menteeProfileData.basicInformation.profileImage)
                        .into(binding.menteeProfileImg)
                }
            }
        }
    }
}