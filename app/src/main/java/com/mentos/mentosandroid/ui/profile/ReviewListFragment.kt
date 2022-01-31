package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentReviewListBinding
import com.mentos.mentosandroid.util.popBackStack

class ReviewListFragment: Fragment() {
    lateinit var binding: FragmentReviewListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewListBinding.inflate(inflater, container, false)

        setBtnBackClickListener()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.reviewBackIb.setOnClickListener {
            popBackStack()
        }
    }
}