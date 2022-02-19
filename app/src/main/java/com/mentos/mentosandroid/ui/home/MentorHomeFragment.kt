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
import com.mentos.mentosandroid.databinding.FragmentHomeMentorBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class MentorHomeFragment() : Fragment() {
    lateinit var binding: FragmentHomeMentorBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMentorBinding.inflate(inflater, container, false)

        initViewModel()
        setMentosObserve()
        setSearchBarClickListener()
        setRainbowBackground()
        setMenteeMoreClickListener()
        setBtnChatClickListener()
        setBtnPushClickListener()

        return binding.root
    }

    private fun setBtnChatClickListener() {
        binding.homeAlarmLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_chatListFragment)
        }
    }

    private fun setBtnPushClickListener() {
        binding.homeBellLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_notificationFragment)
        }
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        homeViewModel.getMentorData()
    }

    private fun setMentosObserve() {
        homeViewModel.mentorHomeData.observe(viewLifecycleOwner) { mentorHomeData ->
            if (mentorHomeData.mentos != null) {
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