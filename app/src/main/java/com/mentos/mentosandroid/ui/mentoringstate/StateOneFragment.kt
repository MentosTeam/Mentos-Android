package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentStateOneBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class StateOneFragment : Fragment() {
    private lateinit var binding: FragmentStateOneBinding
    private val stateViewModel by viewModels<StateViewModel>()
    private val args by navArgs<StateOneFragmentArgs>()
    private var mentoringId = 0
    private var mentoringCategoryId = 0
    private var mentoringCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateOneBinding.inflate(inflater, container, false)
        initData()
        stateViewModel.getRecordList(mentoringId)
        initView()
        setBackBtnClickListener()
        setWriteBtnClickListener()
        setStateAdapter()
        setRecordListObserver()
        return binding.root
    }

    private fun initData() {
        if (args.nowMentoring != null) {
            mentoringId = args.nowMentoring?.mentoringId!!
            mentoringCategoryId = args.nowMentoring?.majorCategoryId!!
            mentoringCount = args.nowMentoring?.mentoringCount!!
        } else if (args.endMentoring != null) {
            mentoringId = args.endMentoring?.mentoringId!!
            mentoringCategoryId = args.endMentoring?.majorCategoryId!!
            mentoringCount = args.endMentoring?.mentoringCount!!
        }
    }

    private fun initView() {
        with(binding) {
            if (args.nowMentoring != null) {
                stateOneCount2.text = args.nowMentoring?.mentoringCount.toString()
                stateOneMentosCount.text = args.nowMentoring?.mentoringMentos.toString()
                stateOneNicknameMentee.text = args.nowMentoring?.mentoringMenteeName
                stateOneNicknameMentor.text = args.nowMentoring?.mentoringMentorName
                stateOneMentosIv.setMentosImg55(args.nowMentoring?.majorCategoryId!!)

            } else if (args.endMentoring != null) {
                stateNowSubTitle.setText(R.string.state_sub_title_end)
                stateOneCount2.text = args.endMentoring?.mentoringCount.toString()
                stateOneMentosCount.text = args.endMentoring?.mentoringMentos.toString()
                stateOneNicknameMentee.text = args.endMentoring?.mentoringMenteeName
                stateOneNicknameMentor.text = args.endMentoring?.mentoringMentorName
                stateOneMentosIv.setMentosImg55(args.endMentoring?.majorCategoryId!!)
            }
        }

        when (SharedPreferenceController.getNowState()) {
            0 -> binding.stateTitleTv.setText(R.string.state_title_mentor)
            1 -> binding.stateTitleTv.setText(R.string.state_title_mentee)
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
                    args.nowMentoring?.mentoringId!!
                )
            )
        }
    }

    private fun setStateAdapter() {
        binding.stateRecordRv.adapter = StateRecordAdapter(mentoringCategoryId)
    }

    private fun setRecordListObserver() {
        stateViewModel.recordList.observe(viewLifecycleOwner) { list ->
            if (SharedPreferenceController.getNowState() == 0 && list.size != mentoringCount) {
                binding.stateOneWriteIb.visibility = View.VISIBLE
            }
            binding.stateOneCount.text = list.size.toString()
            list?.let {
                with(binding.stateRecordRv.adapter as StateRecordAdapter) { submitList(list) }
            }
        }
    }
}