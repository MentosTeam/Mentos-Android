package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileMentorBinding
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

        setPostMoreClickListener()
        setReviewMoreClickListener()

        return binding.root
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