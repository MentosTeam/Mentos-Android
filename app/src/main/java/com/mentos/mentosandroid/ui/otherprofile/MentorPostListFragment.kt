package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentMentorPostListBinding
import com.mentos.mentosandroid.ui.myprofiledetail.PostListViewModel
import com.mentos.mentosandroid.util.popBackStack

class MentorPostListFragment : Fragment() {
    lateinit var binding: FragmentMentorPostListBinding
    private val postListViewModel by viewModels<PostListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorPostListBinding.inflate(inflater, container, false)
        postListViewModel.requestEvent()
        setBtnBackClickListener()
        setSearchListAdapter()
        setSearchMentorObserver()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.mentorPostBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setSearchListAdapter() {
        binding.mentorPostRv.adapter = MentorPostListAdapter()
    }

    private fun setSearchMentorObserver() {
        postListViewModel.dummyList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.mentorPostRv.adapter as MentorPostListAdapter) { submitList(list) }
            }
        }
    }
}