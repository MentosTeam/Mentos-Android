package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.StateEndMentee
import com.mentos.mentosandroid.databinding.ItemStateEndBinding
import com.mentos.mentosandroid.util.EditTextDialog
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosBgStroke
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg59
import com.mentos.mentosandroid.util.OneButtonDialog

class StateEndMenteeAdapter(
    val fragmentManager: FragmentManager,
    val stateViewModel: StateViewModel
) :
    ListAdapter<StateEndMentee, StateEndMenteeAdapter.StateEndMenteeViewHolder>(StateEndDiffUtil()) {

    inner class StateEndMenteeViewHolder(
        private val binding: ItemStateEndBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateEndMentee) {
            with(binding) {
                stateEndMentosIv.setMentosImg59(item.majorCategoryId)
                stateEndContainerLayout.setMentosColor(item.majorCategoryId)

                stateEndCount.text = item.mentoringCount.toString()
                stateEndCount2.text = item.mentoringCount.toString()
                stateEndMentosCount.text = item.mentoringMentos.toString()
                stateEndNicknameMentee.text = item.mentoringMenteeName
                stateEndNicknameMentor.text = item.mentoringMentorName
            }

            binding.stateEndReviewWriteLayout.setOnClickListener {
                OneButtonDialog(3) { rating ->
                    EditTextDialog(0) { reviewText ->
                        stateViewModel.postReview(item.mentoringId, rating!!.toDouble(), reviewText)
                        OneButtonDialog(2) {}.show(fragmentManager, "review")
                    }.show(fragmentManager, "review_text")
                }.show(fragmentManager, "review_star")
            }

            when (item.review) {
                true -> {
                    binding.stateEndReviewDoneLayout.visibility = View.VISIBLE
                    binding.stateEndReviewWriteLayout.visibility = View.GONE
                }
                false -> {
                    // 리뷰 작성 아직 안함
                    binding.stateEndReviewDoneLayout.visibility = View.GONE
                    binding.stateEndReviewWriteLayout.visibility = View.VISIBLE
                    binding.stateEndReviewWriteContainerLayout.setMentosBgStroke(item.majorCategoryId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateEndMenteeViewHolder {
        val binding: ItemStateEndBinding =
            ItemStateEndBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateEndMenteeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateEndMenteeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StateEndDiffUtil : DiffUtil.ItemCallback<StateEndMentee>() {
        override fun areContentsTheSame(oldItem: StateEndMentee, newItem: StateEndMentee): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateEndMentee, newItem: StateEndMentee): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}