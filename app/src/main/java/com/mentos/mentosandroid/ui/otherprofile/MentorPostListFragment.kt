package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentMentorPostListBinding
import com.mentos.mentosandroid.ui.search.SearchMentorAdapter
import com.mentos.mentosandroid.ui.search.SearchViewModel
import com.mentos.mentosandroid.util.popBackStack

class MentorPostListFragment : Fragment() {
    lateinit var binding: FragmentMentorPostListBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val args by navArgs<MentorPostListFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorPostListBinding.inflate(inflater, container, false)
        searchViewModel.requestEvent()
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
        binding.mentorPostRv.adapter = SearchMentorAdapter()
    }

    private fun setSearchMentorObserver() {
        searchViewModel.dummyList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.mentorPostRv.adapter as SearchMentorAdapter) { submitList(list) }
            }
        }
    }
}