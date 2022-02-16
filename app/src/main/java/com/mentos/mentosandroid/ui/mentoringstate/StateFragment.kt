package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentStateBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController

class StateFragment : Fragment() {
    private lateinit var binding: FragmentStateBinding
    private val stateViewModel by viewModels<StateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateBinding.inflate(inflater, container, false)
        initLayout()
        initGetApi()
        setStateAdapter()
        setStateNowObserver()
        setStateEndObserver()
        setStateBeforeObserver()
        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState()) {
            0 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentor)
                binding.stateWaitTitleTv.setText(R.string.state_before_confirm_mentee)
            }
            1 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentee)
                binding.stateWaitTitleTv.setText(R.string.state_before_confirm_mentor)
            }
        }
    }

    private fun initGetApi() {
        when (SharedPreferenceController.getNowState()) {
            0 -> stateViewModel.getStateMentorList()
            1 -> stateViewModel.getStateMenteeList()
        }
    }

    private fun setStateAdapter() {
        binding.stateNowRv.adapter = StateNowAdapter()
        binding.stateEndRv.adapter = StateEndAdapter(childFragmentManager, stateViewModel)
        binding.stateWaitRv.adapter = StateWaitAdapter()
    }

    private fun setStateNowObserver() {
        stateViewModel.nowList.observe(viewLifecycleOwner) { list ->
            binding.nowListSize = list.size
            if (list.isEmpty()) {
                when (SharedPreferenceController.getNowState()) {
                    0 -> binding.stateEndEmptyMessageTv.setText(R.string.state_empty_find_mentee)
                    1 -> binding.stateEndEmptyMessageTv.setText(R.string.state_empty_find_mentor)
                }
            } else {
                binding.stateEndEmptyMessageTv.setText(R.string.state_empty_end)
            }
            list?.let {
                with(binding.stateNowRv.adapter as StateNowAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateEndObserver() {
        stateViewModel.endList.observe(viewLifecycleOwner) { list ->
            binding.endListSize = list.size
            when (SharedPreferenceController.getNowState()) {
                0 -> {
                    when (list.size) {
                        0 -> binding.stateNowEmptyMessageTv.setText(R.string.state_both_empty_now_mentor)
                        else -> binding.stateNowEmptyMessageTv.setText(R.string.state_empty_find_mentee)
                    }
                }
                1 -> {
                    when (list.size) {
                        0 -> binding.stateNowEmptyMessageTv.setText(R.string.state_both_empty_now_mentee)
                        else -> binding.stateNowEmptyMessageTv.setText(R.string.state_empty_find_mentor)
                    }
                }
            }
            list?.let {
                with(binding.stateEndRv.adapter as StateEndAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateBeforeObserver() {
        stateViewModel.waitList.observe(viewLifecycleOwner) { list ->
            binding.waitListSize = list.size
            list?.let {
                with(binding.stateWaitRv.adapter as StateWaitAdapter) { submitList(list) }
            }
        }
    }
}