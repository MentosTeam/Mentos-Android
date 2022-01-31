package com.mentos.mentosandroid.ui.myprofiledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMyPostListBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class MyPostListFragment : Fragment() {
    lateinit var binding: FragmentMyPostListBinding
    private val postListViewModel by viewModels<PostListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPostListBinding.inflate(inflater, container, false)
        postListViewModel.requestEvent()
        setBtnBackClickListener()
        setBtnWriteClickListener()
        setPostListAdapter()
        setSearchMentorObserver()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.myPostBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setBtnWriteClickListener() {
        binding.myPostWriteIb.setOnClickListener {
            navigate(R.id.action_postListFragment_to_searchCreateFragment)
        }
    }

    private fun setPostListAdapter() {
        binding.myPostRv.adapter = PostListAdapter()
    }

    private fun setSearchMentorObserver() {
        postListViewModel.dummyList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.myPostRv.adapter as PostListAdapter) { submitList(list) }
            }
        }
    }
}