package com.mentos.mentosandroid.ui.myprofiledetail

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

class MyPostListAdapter :
    ListAdapter<Search, MyPostListAdapter.PostListViewHolder>(SearchDiffUtil()) {

    inner class PostListViewHolder(
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
//                    MyPostListFragmentDirections.actionPostListFragmentToSearchDetailDialog(
//                        myList = true,
//                        postMento = item
//                    )
//                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val binding: ItemSearchListBinding =
            ItemSearchListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return PostListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
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