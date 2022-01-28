package com.mentos.mentosandroid.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.databinding.ItemSearchListBinding
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.setMentosImg17

class SearchAdapter :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffUtil()) {

    inner class SearchViewHolder(
        private val binding: ItemSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.data = item
            binding.searchMentosIv.setMentosImg17(item.majorCategoryId)

            if(item.imageUrl == null) {
                binding.searchPhotoIv.visibility = View.GONE
            } else {
                binding.searchPhotoIv.visibility = View.VISIBLE
            }

            binding.searchListLayout.setOnClickListener {
                it.navigateWithData(
                    SearchFragmentDirections.actionSearchFragmentToSearchDetailDialog(
                      item
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding: ItemSearchListBinding =
            ItemSearchListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class SearchDiffUtil : DiffUtil.ItemCallback<Search>() {
        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.postTitle == newItem.postTitle
        }
    }
}