package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart1Binding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart1Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart1Binding
    private val args by navArgs<MentoringStart1FragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart1Binding.inflate(inflater, container, false)

        binding.mentoringStart1BackIb.setOnClickListener {
            popBackStack()
        }

        binding.mentoringStart1ButtonBtn.setOnClickListener {
            navigate(R.id.action_mentoringStart1Fragment_to_mentoringStart2Fragment22)
        }


        return binding.root

    }


}