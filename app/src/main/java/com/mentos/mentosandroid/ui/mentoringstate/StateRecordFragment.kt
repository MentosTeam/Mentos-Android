package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentStateRecordBinding
import com.mentos.mentosandroid.util.*

class StateRecordFragment : Fragment() {
    private lateinit var binding: FragmentStateRecordBinding
    private val stateViewModel by viewModels<StateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateRecordBinding.inflate(inflater, container, false)
        binding.viewModel = stateViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBackBtnClickListener()
        setIsSuccessRecordObserve()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.stateRecordBackIb.setOnClickListener {
            setCanRecordObserve()
        }
    }

    private fun setCanRecordObserve() {
        stateViewModel.canRecord.observe(viewLifecycleOwner) { canRecord ->
            when (canRecord) {
                true -> {
                    DialogUtil(5) {
                        popBackStack()
                    }.show(childFragmentManager, "record_stop_write")
                }
                false -> popBackStack()
            }
        }
    }

    private fun setIsSuccessRecordObserve() {
        stateViewModel.isSuccessRecord.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    TwoButtonDialog(0) {
                        OneButtonDialog(0) {
                            popBackStack()
                        }.show(childFragmentManager, "record_complete")
                    }.show(childFragmentManager, "record_confirm")
                }
                false -> {
                }
            }
        }
    }
}