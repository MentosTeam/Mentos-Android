package com.mentos.mentosandroid.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.databinding.ItemHomeMenteeBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil
import com.mentos.mentosandroid.util.navigate

class SearchMenteeAdapter :
    ListAdapter<Mentee, SearchMenteeAdapter.SearchMenteeViewHolder>(SearchDiffUtil()) {

    inner class SearchMenteeViewHolder(
        private val binding: ItemHomeMenteeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Mentee) {
            binding.mentee = item

            //프로필 텍스트 적용
            binding.itemHomeMenteeTv.text =
                item.nickName + "/" + item.menteeMajor + "/" + item.menteeYear + "학번"
            binding.itemHomeMenteeTagTv.text =
                "#" + MentosCategoryUtil.getMentosText(item.firstMajorCategory) +
                        ", #" + MentosCategoryUtil.getMentosText(item.secondMajorCategory)


            binding.itemHomeMenteeLayout.setOnClickListener {
                it.navigate(R.id.action_searchFragment_to_oneMenteeProfileFragment)
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