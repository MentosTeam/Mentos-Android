package com.mentos.mentosandroid.ui.myprofiledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.databinding.FragmentReviewListBinding
import com.mentos.mentosandroid.ui.home.HomeViewModel
import com.mentos.mentosandroid.util.popBackStack

class ReviewListFragment : Fragment() {
    lateinit var binding: FragmentReviewListBinding
    lateinit var reviewViewModel: ReviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewListBinding.inflate(inflater, container, false)

        //뷰모델 연결
        initViewModel()

        setBtnBackClickListener()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.reviewBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun initViewModel() {
        //뷰모델 연결
        reviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        binding.reviewViewModel = reviewViewModel

        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this
    }
}