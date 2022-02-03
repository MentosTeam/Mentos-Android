package com.mentos.mentosandroid.ui.mentoringstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentStateOneBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class StateOneFragment : Fragment() {
    private lateinit var binding: FragmentStateOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStateOneBinding.inflate(inflater, container, false)
        setBackBtnClickListener()
        setWriteBtnClickListener()
        return binding.root
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
}