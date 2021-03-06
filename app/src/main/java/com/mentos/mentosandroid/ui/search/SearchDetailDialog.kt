package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.databinding.DialogSearchDetailBinding
import com.mentos.mentosandroid.ui.myprofiledetail.PostListViewModel
import com.mentos.mentosandroid.util.customdialog.DialogUtil
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.makeToast
import com.mentos.mentosandroid.util.navigate

class SearchDetailDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogSearchDetailBinding
    private val searchViewModel by viewModels<SearchViewModel>()
    private val args by navArgs<SearchDetailDialogArgs>()

    private val postListViewModel by activityViewModels<PostListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        initData()
        initView()
        if (SharedPreferenceController.getMemberId() == args.postMento?.mentoId) {
            setDeleteBtnClickListener()
            setEditBtnClickListener()
        } else {
            setReportBtnClickListener()
            setReportSuccessObserve()
            setLoadingObserve()
        }
        setMentorInfoLayoutClickListener()
        setMentoringStartClickListener()
        setChatBtnClickListener()
        setIsDeletedObserve()
        return binding.root
    }

    private fun setReportBtnClickListener() {
        binding.searchDetailBtnSiren.setOnClickListener {
            EditTextDialog(2) { reportText ->
                postListViewModel.postReport(1, args.postMento!!.postId, reportText)
                this.isCancelable = false
            }.show(childFragmentManager, "report_text")
        }
    }

    private fun setReportSuccessObserve() {
        postListViewModel.isSuccessReport.observe(this) { isSuccess ->
            if (isSuccess) {
                OneButtonDialog(5) {
                    this.dismiss()
                    afterReportNavigate()
                    postListViewModel.initSuccessReport()
                }.show(childFragmentManager, "report")
            }
        }
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

    private fun initView() {
        if (SharedPreferenceController.getMemberId() == args.postMento?.mentoId) {
            binding.searchDetailEditLayout.visibility = View.VISIBLE
            binding.searchDetailBottomMenuLayout.visibility = View.GONE
            binding.searchDetailBtnSiren.visibility = View.GONE
        } else {
            when (args.from) {
                FROM_OTHER_PROFILE -> binding.searchDetailBtnSiren.visibility = View.GONE
                else -> {
                    binding.searchDetailEditLayout.visibility = View.GONE
                    binding.searchDetailBtnSiren.visibility = View.VISIBLE
                }
            }
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

    private fun setChatBtnClickListener() {
        binding.searchBottomChatLayout.setOnClickListener {
            navigateWithData(
                SearchDetailDialogDirections.actionSearchDetailDialogToChatRoomFragment(
                    memberId = args.postMento?.mentoId.toString(),
                    nickname = requireNotNull(args.postMento?.mentoNickName),
                    imageUrl = requireNotNull(args.postMento?.mentoImage),
                )
            )
        }
    }

    private fun setIsDeletedObserve() {
        searchViewModel.isDeletedSuccess.observe(viewLifecycleOwner) { isDeleted ->
            if (isDeleted) {
                makeToast(requireContext(), R.string.toast_write_delete)
                postListViewModel.getMyPostList()
                this.dismiss()
            }
        }
    }


    private fun setLoadingObserve() {
        postListViewModel.setLoading.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> binding.loadingPb.visibility = View.VISIBLE
                else -> binding.loadingPb.visibility = View.GONE
            }
        }
    }

    private fun afterReportNavigate() {
        when (args.from) {
            FROM_HOME_MENTOR -> navigate(R.id.action_searchDetailDialog_to_homeFragment)
            FROM_SEARCH -> navigate(R.id.action_searchDetailDialog_to_searchFragment)
        }
    }

    companion object {
        const val FROM_HOME_MENTOR = "home_mentor"
        const val FROM_SEARCH = "search"
        const val FROM_OTHER_PROFILE = "other_profile"
    }
}