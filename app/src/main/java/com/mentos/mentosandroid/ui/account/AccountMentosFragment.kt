package com.mentos.mentosandroid.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentAccountMentosBinding
import com.mentos.mentosandroid.util.navigateWithData

class AccountMentosFragment : Fragment() {
    private lateinit var binding: FragmentAccountMentosBinding
    private val accountViewModel by activityViewModels<AccountViewModel>()
    private val args by navArgs<AccountMentosFragmentArgs>()
    private lateinit var category: ArrayList<CheckBox>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountMentosBinding.inflate(inflater, container, false)
        binding.viewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        initCategoryArray()
        setBtnCategoryClickListener()
        setCategoryObserve()
        setBtnCompleteClickListener()
        initView()
        return binding.root
    }

    private fun initCategoryArray() {
        category = arrayListOf(
            binding.mentosRedCb,
            binding.mentosOrangeCb,
            binding.mentosYellowCb,
            binding.mentosGreenCb,
            binding.mentosGreenDarkCb,
            binding.mentosSkyCb,
            binding.mentosBlueCb,
            binding.mentosPinkCb,
            binding.mentosPurpleCb,
            binding.mentosBrownLightCb,
            binding.mentosBrownRedCb,
            binding.mentosGrayCb
        )
    }

    private fun setBtnCategoryClickListener() {
        for (i in 0 until category.size) {
            category[i].setCheckBoxClickListener(i + 1)
        }
    }

    private fun setCategoryObserve() {
        accountViewModel.selectedCategory.observe(viewLifecycleOwner) { categoryList ->
            when (categoryList.size) {
                2 -> {
                    for (i in 0 until category.size) {
                        category[i].setCategoryClickUnable()
                    }
                }
                else -> {
                    for (i in 0 until category.size) {
                        category[i].isClickable = true
                    }
                }
            }
        }
    }

    private fun CheckBox.setCheckBoxClickListener(category: Int) {
        this.setOnClickListener {
            if (this.isChecked) {
                accountViewModel.setCategory(category)
            } else {
                accountViewModel.removeCategory(category)
            }
        }
    }

    private fun CheckBox.setCategoryClickUnable() {
        if (!this.isChecked) {
            this.isClickable = false
        }
    }

    private fun initView() {
        when (args.state) {
            2 -> {
                binding.accountMentosTitleTv.setText(R.string.account_intro_title_mentee)
                binding.accountMentosSubTitleTv.setText(R.string.account_mentos_sub_title_mentee)
            }
            1 -> {
                binding.accountMentosTitleTv.setText(R.string.account_intro_title_mentor)
                binding.accountMentosSubTitleTv.setText(R.string.account_mentos_sub_title_mentor)
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