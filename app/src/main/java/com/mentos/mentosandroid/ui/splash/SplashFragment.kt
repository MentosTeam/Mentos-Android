package com.mentos.mentosandroid.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSplashBinding
import com.mentos.mentosandroid.util.navigate

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        setClickListener()
        return binding.root
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