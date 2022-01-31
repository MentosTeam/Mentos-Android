package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentMentoringStart4Binding
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart4Fragment : Fragment() {

    private lateinit var binding: FragmentMentoringStart4Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart4Binding.inflate(inflater, container, false)

        binding.mentoringStart4BackIb.setOnClickListener {
            popBackStack()
        }

        return binding.root
    }
}