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
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class MenteeHomeFragment : Fragment() {
    lateinit var binding: FragmentHomeMenteeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeMenteeBinding.inflate(inflater, container, false)

        initViewModel()
        setMentosObserve()
        setSearchBarClickListener()
        setRainbowBackground()
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
            navigateWithData(
                HomeFragmentDirections.actionHomeFragmentToNotificationFragment(
                    from = "home"
                )
            )
        }
    }


    private fun initViewModel() {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.homeViewModel = homeViewModel
        binding.lifecycleOwner = this

        homeViewModel.getMenteeData()
    }

    private fun setMentosObserve() {
        homeViewModel.menteeHomeData.observe(viewLifecycleOwner) {
            SharedPreferenceController.setMyMentos(
                requireContext(),
                homeViewModel.menteeHomeData.value?.mentos
            )
        }
    }

    private fun setSearchBarClickListener() {
        binding.homeSearchLayout.setOnClickListener {
            navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun setRainbowBackground() {
        val bgHomeTopLayout: GradientDrawable = binding.homeTopLayout.background as GradientDrawable
        bgHomeTopLayout.colors = intArrayOf(
            ContextCompat.getColor(requireContext(), R.color.bg_red),
            ContextCompat.getColor(requireContext(), R.color.bg_orange),
            ContextCompat.getColor(requireContext(), R.color.bg_yellow),
            ContextCompat.getColor(requireContext(), R.color.bg_green),
            ContextCompat.getColor(requireContext(), R.color.bg_blue),
            ContextCompat.getColor(requireContext(), R.color.bg_purple)
        )
        bgHomeTopLayout.orientation = GradientDrawable.Orientation.TL_BR
    }
}