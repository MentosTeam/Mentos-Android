package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSearchBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.MentosCategoryUtil.getMentosText
import com.mentos.mentosandroid.util.MentosCategoryUtil.setSearchCategoryBg
import com.mentos.mentosandroid.util.MentosCategoryUtil.setSearchCategoryTextSize
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val args by navArgs<SearchFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        searchViewModel.requestEvent()
        searchViewModel.requestMenteeList()
        initLayout()
        setHomeSelectedCategory()
        setCategoryBtnLayout()
        setBtnWriteClickListener()
        setKeyBoardVisible()
        setSearchListAdapter()
        setSearchMentorObserver()
        setSearchMenteeObserver()
        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState(requireContext())) {
            0 -> {
                binding.searchMentorListRv.visibility = View.GONE
                binding.searchMenteeListRv.visibility = View.VISIBLE
                binding.searchMentorTitleSubIv.setText(R.string.search_mentee_title_sub)
                binding.searchWriteIb.visibility = View.GONE
            }
            1 -> {
                binding.searchMentorListRv.visibility = View.VISIBLE
                binding.searchMenteeListRv.visibility = View.GONE
                binding.searchMentorTitleSubIv.setText(R.string.search_title_sub)
            }
        }
    }

    private fun setHomeSelectedCategory() {
        when(args.homeCategory) {
            0 -> binding.searchCategoryFirstRb.isChecked = true
            1 -> binding.searchCategorySecondRb.isChecked = true
            3 -> binding.searchCategoryAllRb.isChecked = true
        }
    }

    private fun setCategoryBtnLayout() {
        binding.searchCategoryFirstRb.setSearchCategoryBg(1)
        binding.searchCategoryFirstRb.text = getMentosText(1)
        binding.searchCategoryFirstRb.setSearchCategoryTextSize(1)

        binding.searchCategorySecondRb.setSearchCategoryBg(2)
        binding.searchCategorySecondRb.text = getMentosText(2)
        binding.searchCategorySecondRb.setSearchCategoryTextSize(2)
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
        binding.searchMentorListRv.adapter = SearchMentorAdapter()
        binding.searchMenteeListRv.adapter = SearchMenteeAdapter()
    }

    private fun setSearchMentorObserver() {
        searchViewModel.dummyList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.searchMentorListRv.adapter as SearchMentorAdapter) { submitList(list) }
            }
        }
    }

    private fun setSearchMenteeObserver() {
        searchViewModel.dummyMenteeList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.searchMenteeListRv.adapter as SearchMenteeAdapter) { submitList((list)) }
            }
        }
    }
}