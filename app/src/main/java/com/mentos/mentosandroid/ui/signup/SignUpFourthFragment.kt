package com.mentos.mentosandroid.ui.signup

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentSignUpFourthBinding
import com.mentos.mentosandroid.ui.main.MainActivity

class SignUpFourthFragment : Fragment() {
    private lateinit var binding: FragmentSignUpFourthBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpFourthBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setSuccessSignUpObserve()
        return binding.root
    }

    private fun setSuccessSignUpObserve() {
        signUpViewModel.isSuccessSignUp.observe(viewLifecycleOwner) { isSuccess->
            when(isSuccess) {
                true -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }
}