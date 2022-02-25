package com.mentos.mentosandroid.ui.signup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpFourthBinding
import com.mentos.mentosandroid.ui.main.FirstAccountActivity

class SignUpFourthFragment : Fragment() {
    private lateinit var binding: FragmentSignUpFourthBinding
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpFourthBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setTermsServiceClickListener()
        setTermsPersonalClickListener()
        setSuccessSignUpObserve()
        return binding.root
    }

    private fun setTermsServiceClickListener() {
        binding.signUpTermsServiceTv.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.link_terms_service))
                )
            )
        }
    }

    private fun setTermsPersonalClickListener() {
        binding.signUpTermsPersonalTv.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.link_terms_personal))
                )
            )
        }
    }

    private fun setSuccessSignUpObserve() {
        signUpViewModel.isSuccessSignUp.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), FirstAccountActivity::class.java))
                requireActivity().finish()
            }
        }
    }
}