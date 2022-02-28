package com.mentos.mentosandroid.ui.myprofiledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.data.response.Review
import com.mentos.mentosandroid.databinding.FragmentReviewListBinding
import com.mentos.mentosandroid.ui.home.HomeViewModel
import com.mentos.mentosandroid.util.popBackStack

class ReviewListFragment : Fragment() {
    lateinit var binding: FragmentReviewListBinding
    lateinit var reviewViewModel: ReviewViewModel

    private val args by navArgs<ReviewListFragmentArgs>()
    private lateinit var reviewList: ArrayList<Review>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewListBinding.inflate(inflater, container, false)
        reviewList = args.reviewList.toCollection(ArrayList())

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
        reviewViewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        reviewViewModel.setReviewData(reviewList)
        binding.reviewViewModel = reviewViewModel

        binding.lifecycleOwner = this
    }
}