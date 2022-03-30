package com.mentos.mentosandroid.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.databinding.FragmentSplashBinding
import com.mentos.mentosandroid.ui.main.FirstAccountActivity
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.ui.signin.SignInViewModel
import com.mentos.mentosandroid.util.customdialog.DialogUtil
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.makeToast
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
        setLoadingObserve()
        setIsBlockedUserObserve()
        return binding.root
    }

    private fun setSplash() {
        initSplashView(false)
        Handler(Looper.getMainLooper()).postDelayed(
            {
                binding.splashLayout.visibility = View.GONE
                initSplashView(true)
            }, 1000
        )
    }

    private fun setAutoLogin() {
        signInViewModel.canAutoSignIn.observe(viewLifecycleOwner) { canSignIn ->
            if (canSignIn) {
                initSplashView(false)
                signInViewModel.postSignIn()
            } else {
                setSplash()
            }
        }
    }

    private fun setSuccessSignInObserve() {
        signInViewModel.isSuccessSignIn.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            startActivity(Intent(requireContext(), MainActivity::class.java))
                            requireActivity().finish()
                            makeToast(requireContext(), R.string.toast_auto_login)
                        }, 1000
                    )
                }
                false -> {
                    DialogUtil(0) {
                        binding.splashLayout.visibility = View.GONE
                        initSplashView(true)
                    }.show(
                        childFragmentManager,
                        "sign_in_fail"
                    )
                }
            }
        }
    }

    private fun setIsEmptyProfileObserve() {
        signInViewModel.isEmptyProfile.observe(viewLifecycleOwner) { isEmpty ->
            if (isEmpty) {
                startActivity(Intent(requireContext(), FirstAccountActivity::class.java))
                requireActivity().finish()
            }
        }
    }

    private fun setLoadingObserve() {
        signInViewModel.setLoading.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> binding.splashLoadingPb.visibility = View.VISIBLE
                else -> binding.splashLoadingPb.visibility = View.GONE
            }
        }
    }

    private fun setIsBlockedUserObserve() {
        signInViewModel.blockedUser.observe(viewLifecycleOwner) { isBlock ->
            if (isBlock) {
                OneButtonDialog(6) {
                    binding.splashLayout.visibility = View.GONE
                    initSplashView(true)
                    SharedPreferenceController.setSdfAllClear(requireContext())
                }.show(childFragmentManager, "login_block")
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

    private fun initSplashView(state: Boolean) {
        binding.splashBtnSignUp.isClickable = state
        binding.splashBtnSignIn.isClickable = state
    }
}