package com.mentos.mentosandroid.ui.home

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentHomeMentorBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class MentorHomeFragment(): Fragment() {
    lateinit var binding: FragmentHomeMentorBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMentorBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()
        setSearchBarClickListener()
        setRainbowBackground()
        setMenteeMoreClickListener()
        setBtnNotiClickListener()

        return binding.root
    }

    private fun setBtnNotiClickListener() {
        binding.homeAlarmLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }

    private fun initViewModel() {
        //뷰모델 연결
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this

        //데이터 가져옴
        homeViewModel.getMentorData()

        //가지고 있는 멘토스 개수 저장
        homeViewModel.mentorHomeData.observe(viewLifecycleOwner) { mentorHomeData ->
            if(mentorHomeData.mentos != null){
                SharedPreferenceController.setMyMentos(
                    requireContext(),
                    homeViewModel.mentorHomeData.value?.mentos
                )
            }
        }
    }

    private fun setSearchBarClickListener() {
        binding.homeSearchLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun setRainbowBackground() {
        //homeTopLayout에 무지개 배경 지정
        var bgHomeTopLayout: GradientDrawable = binding.homeTopLayout.background as GradientDrawable
        bgHomeTopLayout.setColors(
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.bg_red),
                ContextCompat.getColor(requireContext(), R.color.bg_orange),
                ContextCompat.getColor(requireContext(), R.color.bg_yellow),
                ContextCompat.getColor(requireContext(), R.color.bg_green),
                ContextCompat.getColor(requireContext(), R.color.bg_blue),
                ContextCompat.getColor(requireContext(), R.color.bg_purple)
            )
        )
        bgHomeTopLayout.orientation = GradientDrawable.Orientation.TL_BR
    }

    private fun setMenteeMoreClickListener() {
        binding.homeOtherMoreImg.setOnClickListener {
            navigateWithData(HomeFragmentDirections.actionHomeFragmentToSearchFragment(3))
        }
    }
}