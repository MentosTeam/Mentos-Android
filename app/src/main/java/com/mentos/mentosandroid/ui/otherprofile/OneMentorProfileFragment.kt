package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.mentos.mentosandroid.databinding.FragmentOneMentorProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileMentosVPAdapter
import com.mentos.mentosandroid.ui.profile.ProfileViewModel
import com.mentos.mentosandroid.util.SharedPreferenceController
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
        initMentosVP()
        initSex()
        setBackBtnClickListener()
        setNavigateWithMentorId()
        return binding.root
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun initMentosVP() {
        val mentosVPAdapter = ProfileMentosVPAdapter(this)
        mentosVPAdapter.mentosList = profileViewModel.mentorMentosList.value!!

        val mentosVP = binding.mentorProfileMentoringMentosVp

        // 좌/우 노출되는 크기를 크게하려면 offsetPx 증가
        val leftOffsetPx = 27.dpToPx(resources.displayMetrics)
        val rightOffsetPx = 18.dpToPx(resources.displayMetrics)
        mentosVP.setPadding(leftOffsetPx, 0, rightOffsetPx, 0)

        // 페이지간 마진 크게하려면 pageMarginPx 증가
        val pageMarginPx = 1.dpToPx(resources.displayMetrics)
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        mentosVP.setPageTransformer(marginTransformer)

        mentosVP.offscreenPageLimit = 3
        mentosVP.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        mentosVP.adapter = mentosVPAdapter
        mentosVP.currentItem = mentosVPAdapter.itemCount
    }

    fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()


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