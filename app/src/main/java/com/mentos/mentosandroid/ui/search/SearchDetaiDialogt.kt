package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.databinding.DialogSearchDetailBinding
import com.mentos.mentosandroid.util.DialogUtil
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17

class SearchDetailDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogSearchDetailBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val args by navArgs<SearchDetailDialogArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initData()
        initMyPostView()
        if (args.myList) {
            setDeleteBtnClickListener()
            setEditBtnClickListener()
        }
        setMentorInfoLayoutClickListener()
        setMentoringStartClickListener()
        setIsDeletedObserve()
        return binding.root
    }

    private fun initData() {
        if (args.postMento != null) {
            with(binding) {
                searchDetailMentorNameTv.text = args.postMento?.mentoNickName
                searchDetailMentosIv.setMentosImg17(args.postMento?.majorCategoryId!!)
                searchDetailContentTv.text = args.postMento?.postContents
                searchDetailTitleTv.text = args.postMento?.postTitle
                searchDetailMentorMajorTv.text = args.postMento?.memberMajor
                if (args.postMento?.imageUrl.equals("null") || args.postMento?.imageUrl == null) {
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

    private fun initMyPostView() {
        if (args.myList) {
            binding.searchDetailEditLayout.visibility = View.VISIBLE
            binding.searchDetailBottomMenuLayout.visibility = View.GONE
        } else {
            binding.searchDetailEditLayout.visibility = View.GONE
        }
    }

    private fun setDeleteBtnClickListener() {
        binding.searchDetailBtnX.setOnClickListener {
            DialogUtil(4) {
                searchViewModel.deleteContent(args.postMento!!.postId)
            }.show(childFragmentManager, "my_post_list_delete")
        }
    }

    private fun setEditBtnClickListener() {
        binding.searchDetailBtnEdit.setOnClickListener {
            navigateWithData(
                SearchDetailDialogDirections.actionSearchDetailDialogToSearchCreateFragment(args.postMento)
            )
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

    private fun setIsDeletedObserve() {
        searchViewModel.isDeletedSuccess.observe(viewLifecycleOwner) { isDeleted ->
            if (isDeleted) {
                Toast.makeText(requireContext(), "글 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show()
                this.dismiss()
            }
        }
    }
}