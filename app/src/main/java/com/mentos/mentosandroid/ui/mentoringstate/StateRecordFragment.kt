package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentStateRecordBinding
import com.mentos.mentosandroid.util.popBackStack

class StateRecordFragment : Fragment() {
    private lateinit var binding: FragmentStateRecordBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateRecordBinding.inflate(inflater, container, false)
        setBackBtnClickListener()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.stateRecordBackIb.setOnClickListener {
            //입력된 글 있으면 dialog
            popBackStack()
        }
    }
}