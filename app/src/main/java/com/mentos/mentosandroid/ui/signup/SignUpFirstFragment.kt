package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpFirstBinding
import com.mentos.mentosandroid.util.navigate

class SignUpFirstFragment : Fragment() {
    private lateinit var binding: FragmentSignUpFirstBinding
    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpFirstBinding.inflate(layoutInflater, container, false)
        setCompleteBtnClickListener()
        return binding.root
    }

    private fun setCompleteBtnClickListener() {
        binding.signUpBtnFirstComplete.setOnClickListener {
            navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
        }
    }

}