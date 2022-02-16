package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpSecondBinding
import com.mentos.mentosandroid.util.customdialog.DialogUtil
import com.mentos.mentosandroid.util.navigate

class SignUpSecondFragment : Fragment() {
    private lateinit var binding: FragmentSignUpSecondBinding
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpSecondBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setCompleteBtnClickListener()
        setEmailConfirmClickListener()
        setIsEmailDuplicateObserve()
        setEmailTextObserve()
        setEmailConfirmObserve()
        setLoadingObserve()
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
        var isClickable = true
        binding.signUpBtnEmailCheckTv.setOnClickListener {
            if (isClickable) {
                isClickable = false
                signUpViewModel.postEmailConfirm()
                setIsDomainValidObserve()
                it.postDelayed(
                    {
                        isClickable = true
                    },
                    5000
                )
            }
        }
    }

    private fun setIsDomainValidObserve() {
        signUpViewModel.isDomainValid.observe(viewLifecycleOwner) { isDomainValid ->
            when (isDomainValid) {
                true -> {
                    binding.signUpDomainFailTv.visibility = View.GONE
                    binding.signUpEmailMessageTv.visibility = View.VISIBLE
                }
                false -> {
                    binding.signUpDomainFailTv.visibility = View.VISIBLE
                    binding.signUpEmailMessageTv.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun setIsEmailDuplicateObserve() {
        signUpViewModel.isEmailDuplicate.observe(viewLifecycleOwner) { isEmailDuplicate ->
            if (isEmailDuplicate) {
                binding.signUpDomainFailTv.setText(R.string.sign_up_email_duplicate)
            } else {
                binding.signUpDomainFailTv.setText(R.string.dialog_school_fail)
            }
        }
    }

    private fun setEmailTextObserve() {
        signUpViewModel.email.observe(viewLifecycleOwner) { email ->
            if (email.isNullOrBlank()) {
                binding.signUpDomainFailTv.visibility = View.GONE
            }
        }
    }

    private fun setEmailConfirmObserve() {
        signUpViewModel.emailConfirm.observe(viewLifecycleOwner) { emailConfirm ->
            if (signUpViewModel.emailConfirmNumber == emailConfirm) {
                signUpViewModel.setEmailConfirm(true)
            } else {
                signUpViewModel.setEmailConfirm(false)
            }

            if (emailConfirm.isNotBlank()) {
                binding.signUpEmailMessageTv.visibility = View.INVISIBLE
            }
        }
    }


    private fun setLoadingObserve() {
        signUpViewModel.setLoading.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> binding.signUpLoadingPb.visibility = View.VISIBLE
                else -> binding.signUpLoadingPb.visibility = View.GONE
            }
        }
    }
}