package com.mentos.mentosandroid.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.databinding.ItemHomeMenteeBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil
import com.mentos.mentosandroid.util.navigateWithData

class SearchMenteeAdapter(val context: Context) :
    ListAdapter<Mentee, SearchMenteeAdapter.SearchMenteeViewHolder>(SearchDiffUtil()) {

    inner class SearchMenteeViewHolder(
        private val binding: ItemHomeMenteeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Mentee) {
            binding.mentee = item

            binding.itemHomeMenteeTv.text =
                item.nickName + "/" + item.menteeMajor + "/" + item.menteeYear
            binding.itemHomeMenteeTagTv.text =
                "#" + MentosCategoryUtil.getMentosText(item.firstMajorCategory) +
                        ", #" + MentosCategoryUtil.getMentosText(item.secondMajorCategory)

            binding.itemHomeMenteeLayout.setOnClickListener {
                it.navigateWithData(
                    SearchFragmentDirections.actionSearchFragmentToOneMenteeProfileFragment(
                        item.menteeStudentId
                    )
                )
            }
            initImg(item.menteeImage)
        }

        private fun initImg(menteeImage: String?) {
            if (menteeImage == null || menteeImage == "null") {
                binding.itemHomeMenteeImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                Glide.with(context)
                    .load(menteeImage)
                    .into(binding.itemHomeMenteeImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMenteeViewHolder {
        val binding: ItemHomeMenteeBinding =
            ItemHomeMenteeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return SearchMenteeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchMenteeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class SearchDiffUtil : DiffUtil.ItemCallback<Mentee>() {
        override fun areContentsTheSame(oldItem: Mentee, newItem: Mentee): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Mentee, newItem: Mentee): Boolean {
            return oldItem.nickName == newItem.nickName
        }
    }
}