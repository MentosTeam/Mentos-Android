package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSearchBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.navigate

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel.requestEvent()
        setBtnWriteClickListener()
        setKeyBoardVisible()
        setSearchListAdapter()
        setSearchListObserver()
        return binding.root
    }

    private fun setBtnWriteClickListener() {
        binding.searchWriteIb.setOnClickListener {
            navigate(R.id.action_searchFragment_to_searchCreateFragment)
        }
    }

    private fun setKeyBoardVisible() {
        binding.searchBarLayout.setOnClickListener {
            KeyBoardUtil.show(requireContext(), binding.searchMainEt)
        }

        binding.searchTopLayout.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setSearchListAdapter() {
        binding.searchListRv.adapter = SearchAdapter()
    }

    private fun setSearchListObserver() {
        searchViewModel.dummyList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.searchListRv.adapter as SearchAdapter) { submitList(list) }
            }
        }
    }
}