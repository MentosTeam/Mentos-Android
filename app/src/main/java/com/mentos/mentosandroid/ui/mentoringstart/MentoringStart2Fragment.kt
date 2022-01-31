package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart2Binding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart2Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart2Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart2Binding.inflate(inflater, container, false)

        binding.mentoringStart2BackIb.setOnClickListener {
            popBackStack()
        }

        binding.mentoringStart2ButtonBtn.setOnClickListener {
            navigate(R.id.action_mentoringStart2Fragment2_to_mentoringStart3Fragment2)
        }



        return binding.root
    }
}