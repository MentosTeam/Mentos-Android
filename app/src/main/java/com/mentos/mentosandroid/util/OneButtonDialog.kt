package com.mentos.mentosandroid.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.DialogOneButtonBinding

class OneButtonDialog(
    private val dialogMode: Int,
    private val doAfterConfirm: (rating: Float?) -> Unit
) :
    DialogFragment() {
    private lateinit var binding: DialogOneButtonBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogOneButtonBinding.inflate(layoutInflater, container, false)
        setLayout()
        initView()
        setStarClickListener()
        setClickListener()
        return binding.root
    }

    private fun setLayout() {
        requireNotNull(dialog).apply {
            requireNotNull(window).apply {
                setBackgroundDrawableResource(R.drawable.shape_gray_stroke_20)
            }
        }
    }

    private fun initView() {
        with(binding) {
            when (dialogMode) {
                RECORD -> {
                    dialogRecordCompleteTitleLayout.visibility = View.VISIBLE
                    dialogWithdrawalCompleteTitleLayout.visibility = View.GONE
                    dialogReviewCompleteTitleLayout.visibility = View.GONE
                    dialogReviewLayout.visibility = View.GONE
                    dialogBgMentos.visibility = View.VISIBLE
                    dialogMentoringCompleteTitleLayout.visibility = View.GONE
                }
                WITHDRAWAL -> {
                    dialogRecordCompleteTitleLayout.visibility = View.GONE
                    dialogWithdrawalCompleteTitleLayout.visibility = View.VISIBLE
                    dialogReviewCompleteTitleLayout.visibility = View.GONE
                    dialogReviewLayout.visibility = View.GONE
                    dialogBtnCompleteTv.visibility = View.GONE
                    dialogBgMentos.visibility = View.VISIBLE
                    dialogMentoringCompleteTitleLayout.visibility = View.GONE
                }
                REVIEW -> {
                    dialogRecordCompleteTitleLayout.visibility = View.GONE
                    dialogWithdrawalCompleteTitleLayout.visibility = View.GONE
                    dialogReviewCompleteTitleLayout.visibility = View.VISIBLE
                    dialogReviewLayout.visibility = View.GONE
                    dialogBgMentos.visibility = View.VISIBLE
                    dialogMentoringCompleteTitleLayout.visibility = View.GONE
                }
                REVIEW_STAR -> {
                    dialogRecordCompleteTitleLayout.visibility = View.GONE
                    dialogWithdrawalCompleteTitleLayout.visibility = View.GONE
                    dialogReviewCompleteTitleLayout.visibility = View.GONE
                    dialogReviewLayout.visibility = View.VISIBLE
                    dialogBtnCompleteTv.visibility = View.GONE
                    dialogBgMentos.visibility = View.GONE
                    dialogMentoringCompleteTitleLayout.visibility = View.GONE
                }
                END_MENTORING -> {
                    dialogRecordCompleteTitleLayout.visibility = View.GONE
                    dialogWithdrawalCompleteTitleLayout.visibility = View.GONE
                    dialogReviewCompleteTitleLayout.visibility = View.GONE
                    dialogReviewLayout.visibility = View.GONE
                    dialogBtnCompleteTv.visibility = View.GONE
                    dialogBgMentos.visibility = View.VISIBLE
                    dialogMentoringCompleteTitleLayout.visibility = View.VISIBLE
                }
                else -> throw IllegalStateException()
            }
        }
    }

    private fun setStarClickListener() {
        binding.dialogReviewRatingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.dialogReviewRatingBar.rating = rating
        }
    }

    private fun setClickListener() {
        binding.dialogBtnCompleteTv.setOnClickListener {
            doAfterConfirm(null)
            dismiss()
        }

        binding.dialogBtnStarCompleteTv.setOnClickListener {
            doAfterConfirm(binding.dialogReviewRatingBar.rating)
            dismiss()
        }

        binding.dialogBtnWithdrawalCompleteTv.setOnClickListener {
            doAfterConfirm(null)
            dismiss()
        }
    }

    companion object {
        const val RECORD = 0
        const val WITHDRAWAL = 1
        const val REVIEW = 2
        const val REVIEW_STAR = 3
        const val END_MENTORING = 4
    }
}