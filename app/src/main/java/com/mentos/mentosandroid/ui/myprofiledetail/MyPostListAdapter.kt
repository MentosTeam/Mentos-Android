package com.mentos.mentosandroid.ui.myprofiledetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.databinding.ItemSearchListBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg17
import com.mentos.mentosandroid.util.navigateWithData

class MyPostListAdapter :
    ListAdapter<SearchMentor, MyPostListAdapter.PostListViewHolder>(SearchDiffUtil()) {

    inner class PostListViewHolder(
        private val binding: ItemSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchMentor) {
            binding.data = item
            binding.searchMentosIv.setMentosImg17(item.majorCategoryId)

            if (item.imageUrl == null) {
                binding.searchPhotoIv.visibility = View.GONE
            } else {
                binding.searchPhotoIv.visibility = View.VISIBLE
            }

            binding.searchListLayout.setOnClickListener {
                it.navigateWithData(
                    MyPostListFragmentDirections.actionPostListFragmentToSearchDetailDialog(
                        postMento = item,
                        from = ""
                    )
                )
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

    private class SearchDiffUtil : DiffUtil.ItemCallback<SearchMentor>() {
        override fun areContentsTheSame(
            oldItem: SearchMentor,
            newItem: SearchMentor
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(
            oldItem: SearchMentor,
            newItem: SearchMentor
        ): Boolean {
            return oldItem.postTitle == newItem.postTitle
        }
    }
}