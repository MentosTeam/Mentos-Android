package com.mentos.mentosandroid.ui.findpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentFindPasswordBinding

class FindPwFragment : Fragment() {
    private lateinit var binding: FragmentFindPasswordBinding
    private val findPwViewModel by viewModels<FindPwViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindPasswordBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}