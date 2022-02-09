package com.mentos.mentosandroid.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSplashBinding
import com.mentos.mentosandroid.ui.main.FirstAccountActivity
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.ui.signin.SignInViewModel
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.navigate

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signInViewModel
        setAutoLogin()
        setClickListener()
        setSuccessSignInObserve()
        setIsEmptyProfileObserve()
        return binding.root
    }

    private fun setAutoLogin() {
        signInViewModel.canAutoSignIn.observe(viewLifecycleOwner) { canSignIn ->
            if (canSignIn) {
                signInViewModel.postSignIn()
            }
        }
    }

    private fun setSuccessSignInObserve() {
        signInViewModel.isSuccessSignIn.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                    Toast.makeText(requireContext(), R.string.toast_auto_login, Toast.LENGTH_SHORT)
                        .show()
                }
                false -> {
                    DialogUtil(0) {}.show(childFragmentManager, "sign_in_fail")
                }
            }
        }
    }

    private fun setIsEmptyProfileObserve() {
        signInViewModel.isEmptyProfile.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                Toast.makeText(requireContext(), R.string.toast_auto_login, Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(requireContext(), FirstAccountActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun setClickListener() {
        binding.splashBtnSignUp.setOnClickListener {
            navigate(R.id.action_splashFragment_to_signUpFirstFragment)
        }
        binding.splashBtnSignIn.setOnClickListener {
            navigate(R.id.action_splashFragment_to_signInFragment)
        }
    }
}