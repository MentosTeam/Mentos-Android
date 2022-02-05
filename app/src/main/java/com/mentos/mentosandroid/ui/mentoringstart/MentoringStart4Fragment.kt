package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart4Binding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg41
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart4Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart4Binding
    private val args by navArgs<MentoringStart4FragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart4Binding.inflate(inflater, container, false)

        initLayout()
        setBtnBackClickListener()
        setBtnRequestClickListener()
        return binding.root
    }

    private fun setBtnRequestClickListener() {
        binding.mentoringStart4ButtonTv.setOnClickListener {
            //+서버 전송

            //start5로 이동
            navigateWithData(
                MentoringStart4FragmentDirections.actionMentoringStart4FragmentToMentoringStart5Fragment(
                    args.mentoringStart
                )
            )
        }
    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart4BackIb.setOnClickListener {
                popBackStack()
        }
    }

    private fun initLayout() {
        val mentoringStart = args.mentoringStart

        binding.mentoringStart4Layout.setMentosColor(mentoringStart.majorCategoryId)
        binding.mentoringStart4MentosCompleteIv.setMentosImg41(mentoringStart.majorCategoryId)
        binding.mentoringStart4MentosCompleteNumberTv.text =
            "멘토링 ${mentoringStart.mentoringCount.toString()}회(멘토-쓰 ${mentoringStart.mentos}개)"
        binding.mentoringStart4MentosCompleteNameTv.text =
            "${mentoringStart.mentoId.toString()} 멘토 / ~~ 멘티"
    }
}