package com.mentos.mentosandroid.ui.home

import android.graphics.drawable.GradientDrawable
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentHomeBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()

        //무지개 배경 지정
        setRainbowBackground()

        setSearchBarClickListener()

        initLayout()

        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState(requireContext())) {
            //멘토
            0 -> {
                binding.homeSearchTv1.setText(R.string.mentor_home_search_main)
                binding.homeSearchTv2.setText(R.string.mentor_home_search_sub)
                binding.homeCategoryTv1.setText(R.string.mentor_home_category_main)
                binding.homeCategoryTv2.setText(R.string.mentor_home_category_sub)
                binding.homeMenteeCategoryRv.visibility = View.VISIBLE
                binding.homeMentorCategoryRv.visibility = View.GONE
                binding.homeOtherTv.setText(R.string.mentor_home_other)
                binding.homeOtherMenteeRv.visibility = View.VISIBLE
                binding.homeOtherMentorRv.visibility = View.GONE
            }
            //멘티
            1 -> {
                binding.homeSearchTv1.setText(R.string.mentee_home_search_main)
                binding.homeSearchTv2.setText(R.string.mentee_home_search_sub)
                binding.homeCategoryTv1.setText(R.string.mentee_home_category_main)
                binding.homeCategoryTv2.setText(R.string.mentee_home_category_sub)
                binding.homeMenteeCategoryRv.visibility = View.GONE
                binding.homeMentorCategoryRv.visibility = View.VISIBLE
                binding.homeOtherTv.setText(R.string.mentee_home_other)
                binding.homeOtherMenteeRv.visibility = View.GONE
                binding.homeOtherMentorRv.visibility = View.VISIBLE
            }
        }
    }

    private fun setSearchBarClickListener() {
        binding.homeSearchLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun initViewModel() {
        //뷰모델 연결
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel

        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this
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