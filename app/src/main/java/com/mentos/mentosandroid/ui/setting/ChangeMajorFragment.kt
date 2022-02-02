package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentChangeMajorBinding

class ChangeMajorFragment : Fragment() {
    private lateinit var binding: FragmentChangeMajorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeMajorBinding.inflate(inflater, container, false)
        return binding.root

    }
}