package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentStateRecordBinding
import com.mentos.mentosandroid.util.*
import com.mentos.mentosandroid.util.customdialog.DialogUtil
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.customdialog.TwoButtonDialog

class StateRecordFragment : Fragment() {
    private lateinit var binding: FragmentStateRecordBinding
    private val stateViewModel by viewModels<StateViewModel>()
    private val args by navArgs<StateRecordFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateRecordBinding.inflate(inflater, container, false)
        binding.viewModel = stateViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBackBtnClickListener()
        setCompleteBtnClickListener()
        setHideKeyboard()
        setIsLastRecordObserve()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.stateRecordBackIb.setOnClickListener {
            if(requireNotNull(stateViewModel.canRecord.value)) {
                DialogUtil(5) {
                    popBackStack()
                }.show(childFragmentManager, "record_stop_write")
            } else {
                popBackStack()
            }
        }
    }

    private fun setCompleteBtnClickListener() {
        binding.stateRecordBtnComplete.setOnClickListener {
            TwoButtonDialog(0) {
                stateViewModel.postRecord(args.mentoringId)
            }.show(childFragmentManager, "record_confirm")
        }
    }

    private fun setHideKeyboard() {
        binding.stateRecordContainerView.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setIsLastRecordObserve() {
        stateViewModel.isLastRecord.observe(viewLifecycleOwner) { isLastRecord ->
            if (isLastRecord) {
                OneButtonDialog(4) {
                    popBackStack()
                }.show(childFragmentManager, "record_complete_last_record")
            } else {
                OneButtonDialog(0) {
                    popBackStack()
                }.show(childFragmentManager, "record_complete")
            }
        }
    }
}