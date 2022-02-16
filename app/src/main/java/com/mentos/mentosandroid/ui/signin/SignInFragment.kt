package com.mentos.mentosandroid.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignInBinding
import com.mentos.mentosandroid.ui.main.FirstAccountActivity
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.customdialog.DialogUtil
import com.mentos.mentosandroid.util.navigate

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signInViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setBtnFindPwClickListener()
        setSuccessSignInObserve()
        setLoadingObserve()
        setIsEmptyProfileObserve()
        return binding.root
    }

    private fun setBtnFindPwClickListener() {
        binding.signInBtnFindPasswordTv.setOnClickListener {
            navigate(R.id.action_signInFragment_to_findPwFragment)
        }
    }

    private fun setSuccessSignInObserve() {
        signInViewModel.isSuccessSignIn.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
                false -> {
                    DialogUtil(0) {}.show(childFragmentManager, "sign_in_fail")
                }
            }
        }
    }

    private fun setLoadingObserve() {
        signInViewModel.setLoading.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> binding.signInLoadingPb.visibility = View.VISIBLE
                else -> binding.signInLoadingPb.visibility = View.GONE
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
}