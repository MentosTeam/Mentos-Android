package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentStateBinding
import com.mentos.mentosandroid.util.SharedPreferenceController

class StateFragment : Fragment() {
    private lateinit var binding: FragmentStateBinding
    private val stateViewModel by viewModels<StateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateBinding.inflate(inflater, container, false)
        stateViewModel.requestEndList()
        stateViewModel.requestNowList()
        initLayout()
        setStateAdapter()
        setStateNowObserver()
        setStateEndObserver()
        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState()) {
            0 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentor)
                binding.stateBeforeTitleTv.setText(R.string.state_before_confirm_mentee)
            }
            1 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentee)
                binding.stateBeforeTitleTv.setText(R.string.state_before_confirm_mentor)
            }
        }
    }

    private fun setStateAdapter() {
        binding.stateNowRv.adapter = StateNowAdapter()
        binding.stateEndRv.adapter = StateEndAdapter(childFragmentManager)
        binding.stateBeforeRv.adapter = StateBeforeAdapter()
    }

    private fun setStateNowObserver() {
        stateViewModel.dummyNowList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.stateNowRv.adapter as StateNowAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateEndObserver() {
        stateViewModel.dummyEndList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.stateEndRv.adapter as StateEndAdapter) { submitList(list) }
            }
        }
    }
}