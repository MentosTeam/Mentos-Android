package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.StateNow
import com.mentos.mentosandroid.databinding.ItemStateBeforeConfirmBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosBgStroke
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55
import com.mentos.mentosandroid.util.navigateWithData

class StateBeforeAdapter :
    ListAdapter<StateNow, StateBeforeAdapter.StateBeforeViewHolder>(StateNowDiffUtil()) {

    inner class StateBeforeViewHolder(
        private val binding: ItemStateBeforeConfirmBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateNow) {
            with(binding) {
                data = item
                stateNowMentosIv.setMentosImg55(item.majorCategoryId)

                stateNowCount.text = item.mentoringCount1.toString()
                stateNowCount2.text = item.mentoringCount2.toString()
                stateNowMentosCount.text = item.mentos.toString()
            }

            binding.stateNowLayout.setOnClickListener {
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