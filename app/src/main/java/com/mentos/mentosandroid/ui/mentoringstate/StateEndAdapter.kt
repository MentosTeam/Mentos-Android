package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.StateEnd
import com.mentos.mentosandroid.databinding.ItemStateEndBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosBgStroke
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg59

class StateEndAdapter :
    ListAdapter<StateEnd, StateEndAdapter.StateEndViewHolder>(StateEndDiffUtil()) {

    inner class StateEndViewHolder(
        private val binding: ItemStateEndBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateEnd) {
            with(binding) {
                data = item
                stateEndMentosIv.setMentosImg59(item.majorCategoryId)
                stateEndContainerLayout.setMentosColor(item.majorCategoryId)

                stateEndCount.text = item.mentoringCount1.toString()
                stateEndCount2.text = item.mentoringCount2.toString()
                stateEndMentosCount.text = item.mentos.toString()
            }

            when (item.review) {
                true -> {
                    binding.stateEndReviewDoneLayout.visibility = View.VISIBLE
                    binding.stateEndReviewWriteLayout.visibility = View.GONE
                }
                false -> {
                    binding.stateEndReviewDoneLayout.visibility = View.GONE
                    binding.stateEndReviewWriteLayout.visibility = View.VISIBLE
                    binding.stateEndReviewWriteContainerLayout.setMentosBgStroke(item.majorCategoryId)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateEndViewHolder {
        val binding: ItemStateEndBinding =
            ItemStateEndBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateEndViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateEndViewHolder, position: Int) {
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