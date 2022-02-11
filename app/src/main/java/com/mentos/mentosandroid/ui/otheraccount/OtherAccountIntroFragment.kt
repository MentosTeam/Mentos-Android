package com.mentos.mentosandroid.ui.otheraccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentAccountIntroBinding
import com.mentos.mentosandroid.databinding.FragmentOtherAccountIntroBinding
import com.mentos.mentosandroid.util.navigate

class OtherAccountIntroFragment : Fragment() {
    private lateinit var binding: FragmentOtherAccountIntroBinding
    private val otherAccountViewModel by activityViewModels<OtherAccountViewModel>()
    private val args by navArgs<OtherAccountIntroFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtherAccountIntroBinding.inflate(inflater, container, false)
        binding.viewModel = otherAccountViewModel
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
                otherAccountViewModel.setState(2)
            }
            1 -> {
                binding.accountIntroTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountIntroSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
                otherAccountViewModel.setState(1)
            }
        }
    }

    private fun setBtnCompleteClickListener() {
        binding.accountIntroBtnComplete.setOnClickListener {
            navigate(R.id.action_otherAccountIntroFragment_to_otherAccountPhotoFragment)
        }
    }
}