package com.mentos.mentosandroid.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignInBinding
import com.mentos.mentosandroid.util.navigate

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private val signInViewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        setBtnFindPwClickListener()
        return binding.root
    }

    private fun setBtnFindPwClickListener() {
        binding.signInBtnFindPasswordTv.setOnClickListener {
            navigate(R.id.action_signInFragment_to_findPwFragment)
        }
    }
}