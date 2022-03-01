package com.mentos.mentosandroid.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentAccountIntroBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.navigate

class AccountIntroFragment : Fragment() {
    private lateinit var binding: FragmentAccountIntroBinding
    private val accountViewModel by activityViewModels<AccountViewModel>()
    private val args by navArgs<AccountIntroFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountIntroBinding.inflate(inflater, container, false)
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        setBtnCompleteClickListener()
        return binding.root
    }

    private fun initView() {
        when (args.state) {
            2 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentee)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentee)
                accountViewModel.setState(2)
            }
            1 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
                accountViewModel.setState(1)
            }
        }

        binding.accountIntroView.setOnClickListener {
            KeyBoardUtil.hide(requireActivity())
        }
    }

    private fun setBtnCompleteClickListener() {
        binding.accountIntroBtnComplete.setOnClickListener {
            navigate(R.id.action_accountIntroFragment_to_accountPhotoFragment)
        }
    }
}