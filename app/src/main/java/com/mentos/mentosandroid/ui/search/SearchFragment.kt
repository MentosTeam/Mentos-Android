package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSearchBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.MentosCategoryUtil.getMentosText
import com.mentos.mentosandroid.util.MentosCategoryUtil.setSearchCategoryBg
import com.mentos.mentosandroid.util.MentosCategoryUtil.setSearchCategoryTextSize
import com.mentos.mentosandroid.data.local.SharedPreferenceController
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
        binding.lifecycleOwner = viewLifecycleOwner
        initLayout()
        if (args.homeCategory == -1) {
            binding.searchCategoryFirstRb.isChecked = true
            binding.searchCategorySecondRb.isChecked = false
            binding.searchCategoryAllRb.isChecked = false
            searchViewModel.getMentosCategoryForFirst()
        }
        setIsCheckedChangeListener()
        setCategoryClickObserve()
        initCategoryBtnLayout()
        setHomeSelectedCategory()
        setSearchTextChangeListener()
        setBtnWriteClickListener()
        setKeyBoardVisible()
        setSearchListAdapter()
        setSearchMentorObserver()
        setSearchMenteeObserver()
        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState()) {
            0 -> {
                binding.searchMentorListRv.visibility = View.GONE
                binding.searchMenteeListRv.visibility = View.VISIBLE
                binding.searchMentorTitleSubIv.setText(R.string.search_mentee_title_sub)
                binding.searchMentorListRv.visibility = View.GONE
                binding.searchMenteeListRv.visibility = View.VISIBLE
                binding.searchMenteeListRv.itemAnimator = null
            }
            1 -> {
                binding.searchMentorListRv.visibility = View.VISIBLE
                binding.searchMenteeListRv.visibility = View.GONE
                binding.searchMentorTitleSubIv.setText(R.string.search_title_sub)
                binding.searchMenteeListRv.visibility = View.GONE
                binding.searchWriteIb.visibility = View.GONE
                binding.searchMentorListRv.visibility = View.VISIBLE
                binding.searchMentorListRv.itemAnimator = null
            }
        }
    }

    private fun setHomeSelectedCategory() {
        when (args.homeCategory) {
            0 -> binding.searchCategoryFirstRb.isChecked = true
            1 -> binding.searchCategorySecondRb.isChecked = true
            3 -> binding.searchCategoryAllRb.isChecked = true
        }
    }

    private fun initCategoryBtnLayout() {
        searchViewModel.firstCategory.observe(viewLifecycleOwner) {
            when (it) {
                0 -> binding.searchCategoryFirstRb.visibility = View.INVISIBLE
                else -> binding.searchCategoryFirstRb.initCategoryView(it)
            }
        }

        searchViewModel.secondCategory.observe(viewLifecycleOwner) {
            when (it) {
                0 -> binding.searchCategorySecondRb.visibility = View.INVISIBLE
                else -> binding.searchCategorySecondRb.initCategoryView(it)
            }
        }
    }

    private fun setIsCheckedChangeListener() {
        binding.searchCategoryFirstRb.setOnCheckedChangeListener { _, isCheck ->
            if (isCheck) {
                searchViewModel.getMentosCategoryForFirst()
            }
        }

        binding.searchCategorySecondRb.setOnCheckedChangeListener { _, isCheck ->
            if (isCheck) {
                searchViewModel.getMentosCategoryForSecond()
            }
        }

        binding.searchCategoryAllRb.setOnCheckedChangeListener { _, isCheck ->
            if (isCheck) {
                searchViewModel.getMentosCategoryForAll()
            }
        }
    }

    private fun RadioButton.initCategoryView(category: Int) {
        this.visibility = View.VISIBLE
        this.text = getMentosText(category)
        this.setSearchCategoryBg(category)
        this.setSearchCategoryTextSize(category)
    }

    private fun setCategoryClickObserve() {
        searchViewModel.firstCategoryClicked.observe(viewLifecycleOwner) { isClick ->
            if (isClick) {
                searchViewModel.clearSearchCategory()
                searchViewModel.setSearchCategory(searchViewModel.firstCategory.value!!)
                searchByCategory()
                setRVScrollTop()
            } else {
                searchViewModel.isCategoryClicked(false)
            }
        }

        searchViewModel.secondCategoryClicked.observe(viewLifecycleOwner) { isClick ->
            if (isClick) {
                searchViewModel.clearSearchCategory()
                searchViewModel.setSearchCategory(searchViewModel.secondCategory.value!!)
                searchByCategory()
                setRVScrollTop()
            } else {
                searchViewModel.isCategoryClicked(false)
            }
        }

        searchViewModel.allCategoryClicked.observe(viewLifecycleOwner) { isClick ->
            if (isClick) {
                searchViewModel.clearSearchCategory()
                for (i in 1..12) {
                    searchViewModel.setSearchCategory(i)
                }
                searchByCategory()
                setRVScrollTop()
            } else {
                searchViewModel.isCategoryClicked(false)
            }
        }
    }

    private fun setRVScrollTop() {
        if (searchViewModel.searchMentorList.value != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.searchMentorListRv.smoothScrollToPosition(0)
            }, 100)
        }
        if (searchViewModel.searchMenteeList.value != null) {
            Handler(Looper.getMainLooper()).postDelayed({
                binding.searchMenteeListRv.smoothScrollToPosition(0)
            }, 100)
        }
    }

    private fun setSearchTextChangeListener() {
        binding.searchMainEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                when (SharedPreferenceController.getNowState()) {
                    0 -> searchViewModel.getMenteeList(s.toString())
                    1 -> searchViewModel.getMentorPostList(s.toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun searchByCategory() {
        searchViewModel.isCategoryClicked(true)
        when (SharedPreferenceController.getNowState()) {
            0 -> searchViewModel.getMenteeList(null)
            1 -> searchViewModel.getMentorPostList(null)
        }
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
        binding.searchMenteeListRv.adapter = SearchMenteeAdapter(requireContext())
    }

    private fun setSearchMentorObserver() {
        searchViewModel.searchMentorList.observe(viewLifecycleOwner) { list ->
            if (SharedPreferenceController.getNowState() == 1) {
                when (list.size) {
                    0 -> {
                        binding.searchMentorEmptyView.visibility = View.VISIBLE
                        binding.searchMentorListRv.visibility = View.GONE
                    }
                    else -> {
                        binding.searchMentorEmptyView.visibility = View.GONE
                        binding.searchMentorListRv.visibility = View.VISIBLE
                    }
                }
            }
            list?.let {
                with(binding.searchMentorListRv.adapter as SearchMentorAdapter) { submitList(list) }
            }
        }
    }

    private fun setSearchMenteeObserver() {
        searchViewModel.searchMenteeList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.searchMenteeListRv.adapter as SearchMenteeAdapter) { submitList((list)) }
            }
        }
    }
}