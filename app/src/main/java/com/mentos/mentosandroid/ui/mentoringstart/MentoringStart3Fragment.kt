package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart3Binding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart3Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart3Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentMentoringStart3Binding.inflate(inflater,container,false)
        binding.mentoringStart3BackArrowIv.setOnClickListener {
            popBackStack()
        }

        binding.mentoringStart3ChooseMentosIv.setOnClickListener {
            val bottomsheet = BottomSheetFragment()
            bottomsheet.setStyle(STYLE_NORMAL,R.style.AppBottomSheetDialogTheme)
            bottomsheet.show(childFragmentManager,bottomsheet.tag)
        }



        binding.mentoringStart3ButtonBtn.setOnClickListener {
            navigate(R.id.action_mentoringStart3Fragment2_to_mentoringStart4Fragment)
        }

        return binding.root
    }
}