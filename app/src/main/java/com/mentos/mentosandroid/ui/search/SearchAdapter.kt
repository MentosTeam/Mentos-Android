package com.mentos.mentosandroid.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.databinding.ItemSearchListBinding
import com.mentos.mentosandroid.util.navigate

class SearchAdapter :
    ListAdapter<Search, SearchAdapter.SearchViewHolder>(SearchDiffUtil()) {

    inner class SearchViewHolder(
        private val binding: ItemSearchListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Search) {
            binding.data = item
            binding.searchPhotoCountTv.text = item.imgCount.toString()

            when (item.major) {
                0 -> setMentosImgSrc(R.drawable.img_mentos_red_17)
                1 -> setMentosImgSrc(R.drawable.img_mentos_orange_17)
                2 -> setMentosImgSrc(R.drawable.img_mentos_yellow_17)
                3 -> setMentosImgSrc(R.drawable.img_mentos_green_17)
                4 -> setMentosImgSrc(R.drawable.img_mentos_green_dark_17)
                5 -> setMentosImgSrc(R.drawable.img_mentos_sky_17)
                6 -> setMentosImgSrc(R.drawable.img_mentos_blue_17)
                7 -> setMentosImgSrc(R.drawable.img_mentos_pink_17)
                8 -> setMentosImgSrc(R.drawable.img_mentos_purple_17)
                9 -> setMentosImgSrc(R.drawable.img_mentos_brown_light_17)
                10 -> setMentosImgSrc(R.drawable.img_mentos_brown_red_17)
                11 -> setMentosImgSrc(R.drawable.img_mentos_gray_17)
                else -> setMentosImgSrc(R.drawable.img_mentos_red_17)
            }

            binding.searchListLayout.setOnClickListener {
                it.navigate(R.id.action_searchFragment_to_searchDetailDialog)
            }
        }

        private fun setMentosImgSrc(img: Int) {
            with(binding) {
                searchMentosIv.setImageResource(img)
                searchMentosMultipleIv.setImageResource(img)
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
            return oldItem.title == newItem.title
        }
    }
}