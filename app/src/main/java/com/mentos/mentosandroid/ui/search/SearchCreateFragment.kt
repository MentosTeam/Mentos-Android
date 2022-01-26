package com.mentos.mentosandroid.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentSearchCreateBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.popBackStack

class SearchCreateFragment : Fragment() {
    private lateinit var binding: FragmentSearchCreateBinding
    private val searchViewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchCreateBinding.inflate(inflater, container, false)
        binding.viewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBtnBackClickListener()
        hideKeyBoard()
        setEtScroll()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.searchBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun hideKeyBoard() {
        binding.searchCreateTabLayout.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setEtScroll() {
        binding.searchCreateContentEt.setTouchForScrollBars()
        binding.searchCreateTitleEt.setTouchForScrollBars()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun EditText.setTouchForScrollBars() {
        setOnTouchListener { view, event ->
            view.parent.requestDisallowInterceptTouchEvent(true)
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_UP -> view.parent.requestDisallowInterceptTouchEvent(false)
            }
            false
        }
    }
}