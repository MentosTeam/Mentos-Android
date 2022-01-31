package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.databinding.DialogSearchDetailBinding
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17

class SearchDetailDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogSearchDetailBinding
    private val args by navArgs<SearchDetailDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchDetailBinding.inflate(inflater, container, false)
        initData()
        setMentorInfoLayoutClickListener()
        setMentoringStartClickListener()
        return binding.root
    }

    private fun initData() {
        if (args.postMento != null) {
            with(binding) {
                searchDetailMentorNameTv.text = args.postMento?.mentoNickName
                searchDetailMentosIv.setMentosImg17(args.postMento?.majorCategoryId!!)
                searchDetailContentTv.text = args.postMento?.postContents
                searchDetailTitleTv.text = args.postMento?.postTitle
                if (args.postMento?.imageUrl == null) {
                    searchDetailPhotoLayout.visibility = View.GONE
                } else {
                    searchDetailPhotoLayout.visibility = View.VISIBLE
                    Glide.with(requireContext())
                        .load(args.postMento?.imageUrl)
                        .into(searchDetailPhotoIv)
                }
            }
        }
    }

    private fun setMentorInfoLayoutClickListener() {
        binding.searchDetailMentorInfoLayout.setOnClickListener {
            navigateWithData(
                SearchDetailDialogDirections.actionSearchDetailDialogToOneMentorProfileFragment(
                    args.postMento?.mentoId!!
                )
            )
        }
    }

    private fun setMentoringStartClickListener() {
        binding.searchBottomMentoringLayout.setOnClickListener {
            navigateWithData(
                SearchDetailDialogDirections.actionSearchDetailDialogToMentoringStart1Fragment(
                    args.postMento?.mentoId!!
                )
            )
        }
    }
}