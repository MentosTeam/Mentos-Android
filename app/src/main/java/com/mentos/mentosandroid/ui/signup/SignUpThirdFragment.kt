package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpThirdBinding
import com.mentos.mentosandroid.util.navigate

class SignUpThirdFragment : Fragment() {
    private lateinit var binding: FragmentSignUpThirdBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpThirdBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setCompleteBtnClickListener()
        return binding.root
    }

    private fun setCompleteBtnClickListener() {
        binding.signUpBtnThirdComplete.setOnClickListener {
            navigate(R.id.action_signUpThirdFragment_to_signUpFourthFragment)
        }
    }
}