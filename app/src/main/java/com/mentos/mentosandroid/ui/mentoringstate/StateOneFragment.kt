package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentStateOneBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class StateOneFragment : Fragment() {
    private lateinit var binding: FragmentStateOneBinding
    private val stateViewModel by viewModels<StateViewModel>()
    private val args by navArgs<StateOneFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateOneBinding.inflate(inflater, container, false)
        if (args.nowMentoring != null) {
            stateViewModel.getRecordList(args.nowMentoring?.mentoringId!!)
        }
        initView()
        setBackBtnClickListener()
        setWriteBtnClickListener()
        setStateAdapter()
        setStateNowObserver()
        return binding.root
    }

    private fun initView() {
        if (args.nowMentoring != null) {
            with(binding) {
                stateOneCount.text = args.nowMentoring?.mentoringCount.toString()
                stateOneCount2.text = args.nowMentoring?.mentoringCount.toString()
                stateOneMentosCount.text = args.nowMentoring?.mentoringMentos.toString()
                stateOneNicknameMentee.text = args.nowMentoring?.mentoringMenteeName
                stateOneNicknameMentor.text = args.nowMentoring?.mentoringMentorName
                stateOneMentosIv.setMentosImg55(args.nowMentoring?.majorCategoryId!!)
            }
        }

        when (SharedPreferenceController.getNowState()) {
            0 -> binding.stateOneWriteIb.visibility = View.VISIBLE
            1 -> binding.stateOneWriteIb.visibility = View.GONE
        }
    }

    private fun setBackBtnClickListener() {
        binding.stateOneBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setWriteBtnClickListener() {
        binding.stateOneWriteIb.setOnClickListener {
            navigateWithData(
                StateOneFragmentDirections.actionStateOneFragmentToStateRecordFragment(
                    args.nowMentoring?.majorCategoryId!!
                )
            )
        }
    }

    private fun setStateAdapter() {
        binding.stateRecordRv.adapter = StateRecordAdapter(args.nowMentoring?.majorCategoryId!!)
    }

    private fun setStateNowObserver() {
        stateViewModel.recordList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.stateRecordRv.adapter as StateRecordAdapter) { submitList(list) }
            }
        }
    }
}