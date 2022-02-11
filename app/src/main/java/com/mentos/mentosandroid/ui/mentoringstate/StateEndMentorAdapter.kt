package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.StateEndMentor
import com.mentos.mentosandroid.databinding.ItemStateEndBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg59

class StateEndMentorAdapter :
    ListAdapter<StateEndMentor, StateEndMentorAdapter.StateEndMentorViewHolder>(StateEndDiffUtil()) {

    inner class StateEndMentorViewHolder(
        private val binding: ItemStateEndBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateEndMentor) {
            with(binding) {
                stateEndMentosIv.setMentosImg59(item.majorCategoryId)
                stateEndContainerLayout.setMentosColor(item.majorCategoryId)

                stateEndCount.text = item.mentoringCount.toString()
                stateEndCount2.text = item.mentoringCount.toString()
                stateEndMentosCount.text = item.mentoringMentos.toString()
                stateEndNicknameMentee.text = item.mentoringMenteeName
                stateEndNicknameMentor.text = item.mentoringMentorName
                stateEndReviewDoneLayout.visibility = View.GONE
                stateEndReviewWriteLayout.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateEndMentorViewHolder {
        val binding: ItemStateEndBinding =
            ItemStateEndBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateEndMentorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateEndMentorViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StateEndDiffUtil : DiffUtil.ItemCallback<StateEndMentor>() {
        override fun areContentsTheSame(oldItem: StateEndMentor, newItem: StateEndMentor): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateEndMentor, newItem: StateEndMentor): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}