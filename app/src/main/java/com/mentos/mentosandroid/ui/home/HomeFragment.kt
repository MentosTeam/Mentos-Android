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
        when (SharedPreferenceController.getNowState(requireContext())) {
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