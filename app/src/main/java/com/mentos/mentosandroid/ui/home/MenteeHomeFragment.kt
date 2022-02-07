package com.mentos.mentosandroid.ui.home

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentHomeMenteeBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class MenteeHomeFragment(): Fragment() {
    lateinit var binding: FragmentHomeMenteeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMenteeBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()
        setSearchBarClickListener()
        setRainbowBackground()
        setBtnNotiClickListener()

        //가지고 있는 멘토스 개수 저장
        SharedPreferenceController.setMyMentos(requireContext(), homeViewModel.menteeHomeData.value?.mentos)
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
}