package com.mentos.mentosandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentHomeBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        initLayout()

        return binding.root
    }

    private fun initLayout() {
        val transaction = childFragmentManager.beginTransaction()
        when (SharedPreferenceController.getNowState()) {
            //멘토
            0 -> {
                val mentorHomeFragment = MentorHomeFragment()
                transaction.replace(R.id.home_fragment_container, mentorHomeFragment).commit()
            }
            //멘티
            1 -> {
                val menteeHomeFragment = MenteeHomeFragment()
                transaction.replace(R.id.home_fragment_container, menteeHomeFragment).commit()
            }
        }
    }


}