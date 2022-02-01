package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentSignUpFourthBinding

class SignUpFourthFragment : Fragment() {
    private lateinit var binding: FragmentSignUpFourthBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpFourthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}