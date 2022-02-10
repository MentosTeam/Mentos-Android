package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentOneMenteeProfileBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.popBackStack

class OneMenteeProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMenteeProfileBinding
    lateinit var profileViewModel: OneProfileViewModel
    private val args by navArgs<OneMenteeProfileFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMenteeProfileBinding.inflate(inflater, container, false)
        initViewModel()
        initSex()
        initImg()
        setBackBtnClickListener()
        return binding.root
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(OneProfileViewModel::class.java)
        profileViewModel.getMenteeProfileData(args.menteeId)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun setBackBtnClickListener() {
        binding.oneMenteeBtnBackIb.setOnClickListener {
            popBackStack()
        }
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

