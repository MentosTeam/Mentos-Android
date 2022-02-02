package com.mentos.mentosandroid.ui.findpassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentFindPasswordBinding
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.popBackStack

class FindPwFragment : Fragment() {
    private lateinit var binding: FragmentFindPasswordBinding
    private val findPwViewModel by viewModels<FindPwViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindPasswordBinding.inflate(layoutInflater, container, false)
        binding.viewModel = findPwViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setGoToSignInClickListener()
        setSuccessFindPwObserve()
        return binding.root
    }

    private fun setGoToSignInClickListener() {
        binding.findPasswordBtnSignInTv.setOnClickListener {
            popBackStack()
        }
    }

    private fun setSuccessFindPwObserve() {
        findPwViewModel.isSuccessFindPw.observe(viewLifecycleOwner) { isSuccess ->
            when (isSuccess) {
                true -> {
                    DialogUtil(3) {}.show(childFragmentManager, "find_pw")
                }
                false -> {
                    DialogUtil(6) {}.show(childFragmentManager, "find_pw_fail")
                }
            }
        }
    }
}