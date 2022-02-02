package com.mentos.mentosandroid.ui.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
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
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        signUpViewModel.setSex("")
        setCompleteBtnClickListener()
        setNickNameCheckClickListener()
        setCheckBoxChangedListener()
        setNickNameObserve()
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
            setNickNameValidObserve()
        }
    }

    private fun setNickNameValidObserve() {
        // nickName 중복 api를 호출 했을때만 이 함수 호출
        signUpViewModel.isNickNameValid.observe(viewLifecycleOwner) { isNickNameValid ->
            when (isNickNameValid) {
                true -> {
                    binding.signUpNickNameSuccessTv.visibility = View.VISIBLE
                    binding.signUpNickNameFailTv.visibility = View.GONE
                }
                false -> {
                    binding.signUpNickNameSuccessTv.visibility = View.GONE
                    binding.signUpNickNameFailTv.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setNickNameObserve() {
        signUpViewModel.nowNickName.observe(viewLifecycleOwner) { nowNickName ->
            if (nowNickName.isNullOrEmpty()) {
                signUpViewModel.setNickNameValid(false)
            }
        }
    }
}