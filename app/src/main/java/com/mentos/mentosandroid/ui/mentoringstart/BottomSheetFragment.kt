package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentBottomSheetBinding
import com.mentos.mentosandroid.util.navigate

class BottomSheetFragment : BottomSheetDialogFragment() {

     private lateinit var binding : FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBottomSheetBinding.inflate(inflater,container,false)

        binding.bottomSheetMentosChooseTv.setOnClickListener {
            dialog?.dismiss()
        }
        return binding.root
    }
}