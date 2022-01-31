package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentOneMentorProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileViewModel
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class OneMentorProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMentorProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    private val args by navArgs<OneMentorProfileFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMentorProfileBinding.inflate(inflater, container, false)
        initViewModel()
        setBackBtnClickListener()
        setNavigateWithMentorId()
        return binding.root
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun setBackBtnClickListener() {
        binding.oneMentorBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setNavigateWithMentorId() {
        binding.mentorProfileDetailMoreIb.setOnClickListener {
            it.navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToMentorPostListFragment(
                    args.mentorId
                )
            )
        }

        binding.mentorProfileBottomMentoringLayout.setOnClickListener {
            it.navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToMentoringStart1Fragment(
                    args.mentorId
                )
            )
        }

        binding.mentorProfileReviewMoreIb.setOnClickListener {
            it.navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToReviewListFragment(
                    args.mentorId
                )
            )
        }
    }
}