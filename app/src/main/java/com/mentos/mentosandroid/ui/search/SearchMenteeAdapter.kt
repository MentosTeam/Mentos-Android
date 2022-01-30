package com.mentos.mentosandroid.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.Profile
import com.mentos.mentosandroid.databinding.ItemHomeProfileBinding
import com.mentos.mentosandroid.util.navigate

class SearchMenteeAdapter :
    ListAdapter<Profile, SearchMenteeAdapter.SearchMenteeViewHolder>(SearchDiffUtil()) {

    inner class SearchMenteeViewHolder(
        private val binding: ItemHomeProfileBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Profile) {
            binding.profile = item

            binding.itemHomeProfileLayout.setOnClickListener {
                it.navigate(R.id.action_searchFragment_to_oneMenteeProfileFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMenteeViewHolder {
        val binding: ItemHomeProfileBinding =
            ItemHomeProfileBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchMenteeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMenteeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class SearchDiffUtil : DiffUtil.ItemCallback<Profile>() {
        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.info == newItem.info
        }
    }
}