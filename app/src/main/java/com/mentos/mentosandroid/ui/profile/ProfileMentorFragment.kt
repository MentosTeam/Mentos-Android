package com.mentos.mentosandroid.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileMentorBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class ProfileMentorFragment : Fragment() {
    private lateinit var binding: FragmentProfileMentorBinding
    private val profileViewModel by activityViewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileMentorBinding.inflate(inflater, container, false)

        initViewModel()
        initSex()
        initImg()
        setPostMoreClickListener()
        setReviewMoreClickListener()
        setFeedbackLayoutClickListener()
        initCreateMenteeView()
        setCreateMenteeClickListener()
        setMentorListObserve()
        return binding.root
    }

    private fun setMentorListObserve() {
        profileViewModel.mentorMentosList.observe(viewLifecycleOwner) { mentorMentosList ->
            if (mentorMentosList != null) {
                initMentosVP(mentorMentosList)
            } else {
                initMentosVP(ArrayList())
            }
        }
    }

    private fun setCreateMenteeClickListener() {
        binding.mentorProfileAddMenteeLayout.setOnClickListener {
            navigateWithData(
                ProfileFragmentDirections.actionProfileFragmentToOtherAccountMentosFragment(
                    2
                )
            )
        }
    }

    private fun setFeedbackLayoutClickListener() {
        binding.mentorProfileInformLayout.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.mentos_gmail)))
            startActivity(intent)
        }
    }

    private fun initCreateMenteeView() {
        profileViewModel.profileState.observe(viewLifecycleOwner) { profileState ->
            when (profileState) {
                1 -> {
                    binding.mentorProfileAddMenteeLayout.visibility = View.VISIBLE
                }
                3 -> {
                    binding.mentorProfileAddMenteeLayout.visibility = View.GONE
                }
            }
        }
    }

    private fun initMentosVP(mentosList: ArrayList<Int>) {
        val mentosVPAdapter = ProfileMentosVPAdapter(this)
        mentosVPAdapter.mentosList = mentosList

        val mentosVP = binding.mentorProfileMentoringMentosVp

        val leftOffsetPx = 27.dpToPx(resources.displayMetrics)
        val rightOffsetPx = 18.dpToPx(resources.displayMetrics)
        mentosVP.setPadding(leftOffsetPx, 0, rightOffsetPx, 0)

        val pageMarginPx = 1.dpToPx(resources.displayMetrics)
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        mentosVP.setPageTransformer(marginTransformer)

        mentosVP.offscreenPageLimit = 3
        mentosVP.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        mentosVP.adapter = mentosVPAdapter
        mentosVP.currentItem = mentosVPAdapter.itemCount
    }

    private fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()

    private fun initSex() {
        val isOpen = SharedPreferenceController.getOpenSex(requireContext())
        if (isOpen) {
            binding.mentorProfileOpenSexLayout.visibility = View.VISIBLE
            binding.mentorProfilePrivateSexLayout.visibility = View.GONE
        } else {
            binding.mentorProfileOpenSexLayout.visibility = View.GONE
            binding.mentorProfilePrivateSexLayout.visibility = View.VISIBLE
        }
    }

    private fun setReviewMoreClickListener() {
        binding.mentorProfileReviewMoreImg.setOnClickListener {
            navigateWithData(
                ProfileFragmentDirections.actionProfileFragmentToReviewListFragment(
                    profileViewModel.mentorProfileData.value!!.reviews.toTypedArray()
                )
            )
        }
    }

    private fun setPostMoreClickListener() {
        binding.mentorProfileDetailMoreImg.setOnClickListener {
            navigate(
                R.id.action_profileFragment_to_postListFragment
            )
        }
    }

    private fun initViewModel() {
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun initImg() {
        profileViewModel.mentorProfileData.observe(viewLifecycleOwner) { mentorProfileData ->
            if (mentorProfileData == null) {
                binding.mentorProfileImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                if (mentorProfileData.basicInformation.profileImage == null) {
                    binding.mentorProfileImg.setImageResource(R.drawable.img_default_mentos)
                } else {
                    Glide.with(requireContext())
                        .load(mentorProfileData.basicInformation.profileImage)
                        .into(binding.mentorProfileImg)
                }
            }
        }
    }
}