package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.StateEnd
import com.mentos.mentosandroid.databinding.ItemStateEndBinding
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosBgStroke
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg59
import com.mentos.mentosandroid.util.navigateWithData

class StateEndAdapter(
    val fragmentManager: FragmentManager,
    val stateViewModel: StateViewModel
) :
    ListAdapter<StateEnd, StateEndAdapter.StateEndMenteeViewHolder>(StateEndDiffUtil()) {

    inner class StateEndMenteeViewHolder(
        private val binding: ItemStateEndBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateEnd) {
            with(binding) {
                data = item
                stateEndMentosIv.setMentosImg59(item.majorCategoryId)
                stateEndContainerLayout.setMentosColor(item.majorCategoryId)

                stateEndCount.text = item.mentoringCount.toString()
                stateEndCount2.text = item.mentoringCount.toString()
                stateEndMentosCount.text = item.mentoringMentos.toString()
            }

            binding.stateEndReviewWriteLayout.setOnClickListener {
                OneButtonDialog(3) { rating ->
                    EditTextDialog(0) { reviewText ->
                        stateViewModel.postReview(item.mentoringId, rating!!.toDouble(), reviewText)
                        OneButtonDialog(2) {
                            stateViewModel.getStateMenteeList()
                        }.show(fragmentManager, "review")
                    }.show(fragmentManager, "review_text")
                }.show(fragmentManager, "review_star")
            }

            if (SharedPreferenceController.getNowState() == 1) {
                when (item.reviewCheck) {
                    1 -> {
                        binding.stateEndReviewDoneLayout.visibility = View.VISIBLE
                        binding.stateEndReviewWriteLayout.visibility = View.GONE
                    }
                    0 -> {
                        binding.stateEndReviewDoneLayout.visibility = View.GONE
                        binding.stateEndReviewWriteLayout.visibility = View.VISIBLE
                        binding.stateEndReviewWriteContainerLayout.setMentosBgStroke(item.majorCategoryId)
                    }
                }
            }

            binding.stateEndLayout.setOnClickListener {
                it.navigateWithData(
                    StateFragmentDirections.actionStateFragmentToStateOneFragment(
                        nowMentoring = null,
                        endMentoring = item
                    )
                )
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

    private class StateEndDiffUtil : DiffUtil.ItemCallback<StateEnd>() {
        override fun areContentsTheSame(oldItem: StateEnd, newItem: StateEnd): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateEnd, newItem: StateEnd): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}