package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentSignUpFirstBinding
import com.mentos.mentosandroid.util.navigate

class SignUpFirstFragment : Fragment() {
    private lateinit var binding: FragmentSignUpFirstBinding
    private val signUpViewModel by activityViewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpFirstBinding.inflate(layoutInflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        signUpViewModel.setSex("")
        setCompleteBtnClickListener()
        setNickNameCheckClickListener()
        setCheckBoxChangedListener()
        setNickNameObserve()
        setNameValidObserve()
        return binding.root
    }

    private fun setCompleteBtnClickListener() {
        binding.signUpBtnFirstComplete.setOnClickListener {
            navigate(R.id.action_signUpFirstFragment_to_signUpSecondFragment)
        }
    }

    private fun setCheckBoxChangedListener() {
        binding.signUpSexMenCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                signUpViewModel.setSex("M")
            }
            binding.signUpSexWomenCb.isChecked = !binding.signUpSexMenCb.isChecked
        }

        binding.signUpSexWomenCb.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                signUpViewModel.setSex("F")
            }
            binding.signUpSexMenCb.isChecked = !binding.signUpSexWomenCb.isChecked
        }
    }

    private fun setNickNameCheckClickListener() {
        binding.signUpNickNameCheckTv.setOnClickListener {
            signUpViewModel.getNickNameValid()
            signUpViewModel.checkedNickName.value = signUpViewModel.nowNickName.value
        }
    }

    private fun setNickNameObserve() {
        signUpViewModel.nowNickName.observe(viewLifecycleOwner) { nowNickName ->
            if (nowNickName.isNullOrEmpty()) {
                signUpViewModel.setNickNameValid(true)
            }
        }
    }

    private fun setNameValidObserve() {
        signUpViewModel.isNameValid.observe(viewLifecycleOwner) { isNameValid ->
            when (isNameValid) {
                true -> binding.signUpNameMessageTv.visibility = View.GONE
                false -> binding.signUpNameMessageTv.visibility = View.VISIBLE
            }
        }
    }
}