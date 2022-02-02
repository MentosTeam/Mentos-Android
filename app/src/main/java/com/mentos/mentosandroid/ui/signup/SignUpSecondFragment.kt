package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpSecondBinding
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.navigate

class SignUpSecondFragment : Fragment() {
    private lateinit var binding: FragmentSignUpSecondBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpSecondBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setCompleteBtnClickListener()
        setEmailConfirmClickListener()
        return binding.root
    }

    private fun setCompleteBtnClickListener() {
        binding.signUpBtnSecondComplete.setOnClickListener {
            signUpViewModel.isEmailConfirmValid.observe(viewLifecycleOwner) { isEmailConfirmValid ->
                when (isEmailConfirmValid) {
                    true -> navigate(R.id.action_signUpSecondFragment_to_signUpThirdFragment)
                    false -> {
                        if (!isEmailConfirmValid) {
                            DialogUtil(2) {

                            }.show(childFragmentManager, "email_confirm_fail")
                        }
                    }
                }
            }
        }
    }

    private fun setEmailConfirmClickListener() {
        binding.signUpBtnEmailCheckTv.setOnClickListener {
            signUpViewModel.postEmailConfirm()
            setIsDomainValidObserve()
        }
    }

    private fun setIsDomainValidObserve() {
        signUpViewModel.isDomainValid.observe(viewLifecycleOwner) { isDomainValid ->
            if (!isDomainValid) {
                DialogUtil(1) { }.show(childFragmentManager, "email_domain_fail")
            }
        }
    }
}