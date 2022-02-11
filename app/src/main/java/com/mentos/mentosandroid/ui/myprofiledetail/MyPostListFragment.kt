package com.mentos.mentosandroid.ui.myprofiledetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.databinding.FragmentMyPostListBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class MyPostListFragment : Fragment() {
    lateinit var binding: FragmentMyPostListBinding
    private val postListViewModel by activityViewModels<PostListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyPostListBinding.inflate(inflater, container, false)
        postListViewModel.getMyPostList()
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
        binding.myPostRv.adapter = MyPostListAdapter()
    }

    private fun setSearchMentorObserver() {
        postListViewModel.myPostList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.myPostRv.adapter as MyPostListAdapter) {
                    submitList(list)
                }
            }
        }
    }
}