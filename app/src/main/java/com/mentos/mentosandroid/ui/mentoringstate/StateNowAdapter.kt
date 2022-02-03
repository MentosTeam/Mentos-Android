package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.StateNow
import com.mentos.mentosandroid.databinding.ItemStateNowBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosBgStroke
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg55
import com.mentos.mentosandroid.util.navigateWithData

class StateNowAdapter :
    ListAdapter<StateNow, StateNowAdapter.StateNowViewHolder>(StateNowDiffUtil()) {

    inner class StateNowViewHolder(
        private val binding: ItemStateNowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateNow) {
            with(binding) {
                data = item
                stateNowMentosIv.setMentosImg55(item.majorCategoryId)
                stateNowLayout.setMentosBgStroke(item.majorCategoryId)

                stateNowCount.text = item.mentoringCount1.toString()
                stateNowCount2.text = item.mentoringCount2.toString()
                stateNowMentosCount.text = item.mentos.toString()
            }

            binding.stateNowLayout.setOnClickListener {
                it.navigateWithData(
                    StateFragmentDirections.actionStateFragmentToStateOneFragment(
                        item
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateNowViewHolder {
        val binding: ItemStateNowBinding =
            ItemStateNowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateNowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateNowViewHolder, position: Int) {
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