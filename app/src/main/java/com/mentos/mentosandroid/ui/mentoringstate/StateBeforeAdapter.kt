package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.StateNow
import com.mentos.mentosandroid.databinding.ItemStateBeforeConfirmBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55

class StateBeforeAdapter :
    ListAdapter<StateNow, StateBeforeAdapter.StateBeforeViewHolder>(StateNowDiffUtil()) {

    inner class StateBeforeViewHolder(
        private val binding: ItemStateBeforeConfirmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateNow) {
            with(binding) {
                data = item
                stateBeforeMentosIv.setMentosImg55(item.majorCategoryId)

                stateBeforeCount.text = item.mentoringCount.toString()
                stateBeforeCount2.text = item.mentoringCount.toString()
                stateBeforeMentosCount.text = item.mentoringMentos.toString()
            }

            binding.stateBeforeLayout.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateBeforeViewHolder {
        val binding: ItemStateBeforeConfirmBinding =
            ItemStateBeforeConfirmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateBeforeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateBeforeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StateNowDiffUtil : DiffUtil.ItemCallback<StateNow>() {
        override fun areContentsTheSame(oldItem: StateNow, newItem: StateNow): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateNow, newItem: StateNow): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}