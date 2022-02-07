package com.mentos.mentosandroid.ui.otherprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.databinding.ItemSearchListBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17
import com.mentos.mentosandroid.util.navigateWithData

class MentorPostListAdapter :
    ListAdapter<Search, MentorPostListAdapter.MentorPostListViewHolder>(SearchDiffUtil()) {

    inner class MentorPostListViewHolder(
        private val binding: ItemSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.searchMentosIv.setMentosImg17(item.majorCategoryId)

            if (item.imageUrl == null) {
                binding.searchPhotoIv.visibility = View.GONE
            } else {
                binding.searchPhotoIv.visibility = View.VISIBLE
            }

            binding.searchListLayout.setOnClickListener {
//                it.navigateWithData(
//                    MentorPostListFragmentDirections.actionMentorPostListFragmentToSearchDetailDialog(
//                        myList = false,
//                        postMento = item
//                    )
//                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorPostListViewHolder {
        val binding: ItemSearchListBinding =
            ItemSearchListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MentorPostListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorPostListViewHolder, position: Int) {
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