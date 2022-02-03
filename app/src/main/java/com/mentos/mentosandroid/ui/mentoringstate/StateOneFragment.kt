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
import com.mentos.mentosandroid.util.navigate
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
        stateViewModel.requestRecordList()
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
                stateOneCount.text = args.nowMentoring?.mentoringCount1.toString()
                stateOneCount2.text = args.nowMentoring?.mentoringCount2.toString()
                stateOneMentosCount.text = args.nowMentoring?.mentos.toString()
                stateOneNicknameMentee.text = args.nowMentoring?.mentiNickname
                stateOneNicknameMentor.text = args.nowMentoring?.mentoNickname
                stateOneMentosIv.setMentosImg55(args.nowMentoring?.majorCategoryId!!)
            }
        }
    }

    private fun setBackBtnClickListener() {
        binding.stateOneBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setWriteBtnClickListener() {
        binding.stateOneWriteIb.setOnClickListener {
            navigate(R.id.action_stateOneFragment_to_stateRecordFragment)
        }
    }

    private fun setStateAdapter() {
        binding.stateRecordRv.adapter = StateRecordAdapter()
    }

    private fun setStateNowObserver() {
        stateViewModel.dummyRecordList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.stateRecordRv.adapter as StateRecordAdapter) { submitList(list) }
            }
        }
    }
}