package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentMentoringStart5Binding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg41
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart5Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart5Binding
    private val args by navArgs<MentoringStart5FragmentArgs>()
    private val mentoringStartViewModel by activityViewModels<MentoringStartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart5Binding.inflate(inflater, container, false)

        initLayout()
        setBtnBackClickListener()
        return binding.root
    }

    private fun initLayout() {
        val mentoringStart = args.mentoringStart
        val stateWait = args.stateWait

        if (mentoringStart != null) {
            mentoringStartViewModel.nickName.observe(viewLifecycleOwner) { nickName ->
                if (nickName != null) {
                    binding.mentoringStart5MentosCompleteNameTv.text =
                        "${nickName.mentoNickname} 멘토 / ${nickName.mentiNickname} 멘티"

                    binding.mentoringStart5Layout.setMentosColor(mentoringStart.majorCategoryId)
                    binding.mentoringStart5MentosCompleteIv.setMentosImg41(mentoringStart.majorCategoryId)
                    binding.mentoringStart5MentosCompleteNumberTv.text =
                        "멘토링 ${mentoringStart.mentoringCount.toString()}회(멘토-쓰 ${mentoringStart.mentos}개)"
                }
            }
        } else if (stateWait != null) {
            binding.mentoringStart5MentosCompleteNameTv.text =
                "${stateWait.mentoringMentorName} 멘토 / ${stateWait.mentoringMenteeName} 멘티"

            binding.mentoringStart5Layout.setMentosColor(stateWait.majorCategoryId)
            binding.mentoringStart5MentosCompleteIv.setMentosImg41(stateWait.majorCategoryId)
            binding.mentoringStart5MentosCompleteNumberTv.text =
                "멘토링 ${stateWait.mentoringCount.toString()}회(멘토-쓰 ${stateWait.mentoringMentos}개)"
        }

    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart5CloseIb.setOnClickListener {
            popBackStack()
        }
    }
}