package com.mentos.mentosandroid.ui.otheraccount

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
import com.mentos.mentosandroid.databinding.FragmentOtherAccountMentosBinding
import com.mentos.mentosandroid.util.navigateWithData

class OtherAccountMentosFragment : Fragment() {
    private lateinit var binding: FragmentOtherAccountMentosBinding
    private val otherAccountViewModel by activityViewModels<OtherAccountViewModel>()
    private val args by navArgs<OtherAccountMentosFragmentArgs>()
    private lateinit var category: ArrayList<CheckBox>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtherAccountMentosBinding.inflate(inflater, container, false)
        binding.viewModel = otherAccountViewModel
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
        otherAccountViewModel.selectedCategory.observe(viewLifecycleOwner) { categoryList ->
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
                otherAccountViewModel.setCategory(category)
            } else {
                otherAccountViewModel.removeCategory(category)
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
                OtherAccountMentosFragmentDirections.actionOtherAccountMentosFragmentToOtherAccountIntroFragment(
                    args.state
                )
            )
        }
    }
}