package com.mentos.mentosandroid.ui.mentoringstate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.StateRecord
import com.mentos.mentosandroid.databinding.ItemStateRecordBinding

class StateRecordAdapter :
    ListAdapter<StateRecord, StateRecordAdapter.StateRecordViewHolder>(StateRecordDiffUtil()) {

    inner class StateRecordViewHolder(
        private val binding: ItemStateRecordBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StateRecord) {
            binding.data = item
            binding.stateRecordCountTv.text = item.count.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateRecordViewHolder {
        val binding: ItemStateRecordBinding =
            ItemStateRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return StateRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StateRecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class StateRecordDiffUtil : DiffUtil.ItemCallback<StateRecord>() {
        override fun areContentsTheSame(oldItem: StateRecord, newItem: StateRecord): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: StateRecord, newItem: StateRecord): Boolean {
            return oldItem.mentoringId == newItem.mentoringId
        }
    }
}