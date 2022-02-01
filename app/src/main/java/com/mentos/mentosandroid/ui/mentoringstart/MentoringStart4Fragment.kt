package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentMentoringStart4Binding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg41
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

        binding.mentoringStart4BackIb.setOnClickListener {
            popBackStack()
        }

        initLayout()

        return binding.root
    }

    private fun initLayout() {
        var mentoringStart = args.mentoringStart

        binding.mentoringStart4Layout.setMentosColor(mentoringStart.majorCategoryId)
        binding.mentoringStart4MentosCompleteIv.setMentosImg41(mentoringStart.majorCategoryId)
        binding.mentoringStart4MentosCompleteNumberTv.text =
            "${mentoringStart.majorCategoryId.toString()}회"
        binding.mentoringStart4MentosCompleteNameTv.text =
            "${mentoringStart.mentoId.toString()} 멘토 / ~~ 멘티"
    }
}