package com.mentos.mentosandroid.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentAccountMentosBinding
import com.mentos.mentosandroid.util.navigateWithData

class AccountMentosFragment : Fragment() {
    private lateinit var binding: FragmentAccountMentosBinding
    private val accountViewModel by viewModels<AccountViewModel>()
    private val args by navArgs<AccountMentosFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountMentosBinding.inflate(inflater, container, false)
        setBtnCompleteClickListener()
        initView()
        return binding.root
    }

    private fun initView() {
        when (args.state) {
            2 -> {
                binding.accountMentosTitleTv.setText(R.string.account_intro_title_mentee)
                binding.accountMentosSubTitleTv.setText(R.string.account_intro_sub_title_mentee)
            }
            1 -> {
                binding.accountMentosTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountMentosSubTitleTv.setText(R.string.account_intro_sub_title_mentor)
            }
        }
    }

    private fun setBtnCompleteClickListener() {
        binding.accountMentosBtnComplete.setOnClickListener {
            navigateWithData(
                AccountMentosFragmentDirections.actionAccountMentosFragmentToAccountIntroFragment(
                    args.state
                )
            )
        }
    }
}