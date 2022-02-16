package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart3Binding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosText
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg71
import com.mentos.mentosandroid.util.customdialog.MentosCategoryDialog
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart3Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart3Binding
    private val args by navArgs<MentoringStart3FragmentArgs>()

    var mentosCategory = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart3Binding.inflate(inflater, container, false)

        //mentosingStart4에서 back버튼 눌렀을 때 사용
        initLayout()

        setBtnBackClickListener()
        setBtnSelectClickListener()
        setBtnStartClickListener()

        return binding.root
    }

    private fun initLayout() {
        if (mentosCategory != -1) {
            binding.mentoringStart3SelectedImg.setMentosImg71(mentosCategory)
            binding.mentoringStart3SelectedTv.setMentosText(mentosCategory)
            binding.mentoringStart3SelectTv.visibility = View.GONE
            binding.mentoringStart3ButtonTv.setBackgroundResource(R.drawable.shape_black_fill_8)
            binding.mentoringStart3ButtonTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.mentoringStart3BackgroundImg.visibility = View.GONE
        }
    }

    private fun setBtnStartClickListener() {
        binding.mentoringStart3ButtonTv.setOnClickListener {
            if (mentosCategory != -1) {
                var mentoringStart = args.mentoringStart
                mentoringStart.majorCategoryId = mentosCategory
                navigateWithData(
                    MentoringStart3FragmentDirections.actionMentoringStart3Fragment2ToMentoringStart4Fragment(
                        mentoringStart
                    )
                )
            }
        }
    }

    private fun setBtnSelectClickListener() {
        binding.mentoringStart3ChooseMentosLayout.setOnClickListener {
            MentosCategoryDialog { category ->
                if (category != 0) {
                    mentosCategory = category
                    binding.mentoringStart3SelectedImg.setMentosImg71(category)
                    binding.mentoringStart3SelectedTv.setMentosText(category)
                    binding.mentoringStart3SelectTv.visibility = View.GONE
                    binding.mentoringStart3ButtonTv.text = getString(R.string.mentoring_start3_complete_next_button)
                    binding.mentoringStart3ButtonTv.setBackgroundResource(R.drawable.shape_black_fill_8)
                    binding.mentoringStart3ButtonTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    binding.mentoringStart3BackgroundImg.visibility = View.GONE
                }
            }.show(childFragmentManager, "SELECT_MENTORING_START")
        }
    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart3BackIb.setOnClickListener {
            popBackStack()
        }
    }
}