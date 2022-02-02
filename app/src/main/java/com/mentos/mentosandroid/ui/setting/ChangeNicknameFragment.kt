package com.mentos.mentosandroid.ui.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChangeNicknameBinding

class ChangeNicknameFragment : Fragment() {
    private lateinit var binding: FragmentChangeNicknameBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeNicknameBinding.inflate(inflater, container, false)

        return binding.root
    }
}