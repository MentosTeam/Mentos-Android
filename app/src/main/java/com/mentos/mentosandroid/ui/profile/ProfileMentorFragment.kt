package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileMentorBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class ProfileMentorFragment : Fragment() {
    private lateinit var binding: FragmentProfileMentorBinding
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileMentorBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()
        initSex()
        setPostMoreClickListener()
        setReviewMoreClickListener()

        initMentosVP()

        return binding.root
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

    private fun setReviewMoreClickListener() {
        binding.mentorProfileReviewMoreImg.setOnClickListener {
            navigate(R.id.action_profileFragment_to_reviewListFragment)
        }
    }

    private fun setPostMoreClickListener() {
        binding.mentorProfileDetailMoreImg.setOnClickListener {
            navigate(R.id.action_profileFragment_to_postListFragment)
        }
    }

    private fun initViewModel() {
        //뷰모델 연결
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel

        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this
    }
}