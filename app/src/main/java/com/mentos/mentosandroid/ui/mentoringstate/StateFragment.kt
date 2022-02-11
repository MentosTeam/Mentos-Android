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
        initLayout()
        initGetApi()
        setStateAdapter()
        setStateNowObserver()
        setStateEndMentorObserver()
        setStateEndMenteeObserver()
        setStateBeforeObserver()
        return binding.root
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState()) {
            0 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentor)
                binding.stateBeforeTitleTv.setText(R.string.state_before_confirm_mentee)
                binding.stateEndMentorRv.visibility = View.VISIBLE
                binding.stateEndMenteeRv.visibility = View.GONE
            }
            1 -> {
                binding.stateTitleTv.setText(R.string.state_title_mentee)
                binding.stateBeforeTitleTv.setText(R.string.state_before_confirm_mentor)
                binding.stateEndMentorRv.visibility = View.GONE
                binding.stateEndMenteeRv.visibility = View.VISIBLE
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
        binding.stateEndMentorRv.adapter = StateEndMentorAdapter()
        binding.stateEndMenteeRv.adapter =
            StateEndMenteeAdapter(childFragmentManager, stateViewModel)
        binding.stateBeforeRv.adapter = StateBeforeAdapter()
    }

    private fun setStateNowObserver() {
        stateViewModel.nowList.observe(viewLifecycleOwner) { list ->
            binding.nowListSize = list.size
            if (list.isEmpty()) {
                when (SharedPreferenceController.getNowState()) {
                    0 -> binding.stateEndEmptyMessageTv.setText(R.string.state_both_empty_now_mentee)
                    1 -> binding.stateEndEmptyMessageTv.setText(R.string.state_both_empty_now_mentor)
                }
            } else {
                binding.stateEndEmptyMessageTv.setText(R.string.state_empty_end)
            }
            list?.let {
                with(binding.stateNowRv.adapter as StateNowAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateEndMentorObserver() {
        stateViewModel.endMentorList.observe(viewLifecycleOwner) { list ->
            if (SharedPreferenceController.getNowState() == 0) {
                binding.endListSize = list.size
                when (list.size) {
                    0 -> binding.stateNowEmptyMessageTv.setText(R.string.state_both_empty_now_mentor)
                    else -> binding.stateNowEmptyMessageTv.setText(R.string.state_empty_find_mentee)
                }
            }
            list?.let {
                with(binding.stateEndMentorRv.adapter as StateEndMentorAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateEndMenteeObserver() {
        stateViewModel.endMenteeList.observe(viewLifecycleOwner) { list ->
            if (SharedPreferenceController.getNowState() == 1) {
                binding.endListSize = list.size
                when (list.size) {
                    0 -> binding.stateNowEmptyMessageTv.setText(R.string.state_both_empty_now_mentee)
                    else -> binding.stateNowEmptyMessageTv.setText(R.string.state_empty_find_mentor)
                }
            }
            list?.let {
                with(binding.stateEndMenteeRv.adapter as StateEndMenteeAdapter) { submitList(list) }
            }
        }
    }

    private fun setStateBeforeObserver() {
        stateViewModel.beforeList.observe(viewLifecycleOwner) { list ->
            binding.beforeListSize = list.size
            list?.let {
                with(binding.stateBeforeRv.adapter as StateBeforeAdapter) { submitList(list) }
            }
        }
    }
}