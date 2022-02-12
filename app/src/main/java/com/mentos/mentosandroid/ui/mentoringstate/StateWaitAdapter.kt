package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.StateWait
import com.mentos.mentosandroid.databinding.ItemStateWaitBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55

class StateWaitAdapter :
    ListAdapter<StateWait, StateWaitAdapter.StateWaitViewHolder>(StateNowDiffUtil()) {

    inner class StateWaitViewHolder(
        private val binding: ItemStateWaitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateWait) {
            with(binding) {
                data = item
                stateWaitMentosIv.setMentosImg55(item.majorCategoryId)
                stateWaitCountAll.text = item.mentoringCount.toString()
                stateWaitMentosCount.text = item.mentoringMentos.toString()
            }

            binding.stateWaitLayout.setOnClickListener {
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateWaitViewHolder {
        val binding: ItemStateWaitBinding =
            ItemStateWaitBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateWaitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateWaitViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StateNowDiffUtil : DiffUtil.ItemCallback<StateWait>() {
        override fun areContentsTheSame(oldItem: StateWait, newItem: StateWait): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateWait, newItem: StateWait): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}