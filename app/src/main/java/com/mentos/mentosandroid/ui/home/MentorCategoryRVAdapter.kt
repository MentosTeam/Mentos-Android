package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.MentorCategory
import com.mentos.mentosandroid.databinding.ItemMenteeHomeCategoryBinding
import com.mentos.mentosandroid.util.navigateWithData

class MentorCategoryRVAdapter() :
    RecyclerView.Adapter<MentorCategoryRVAdapter.MentorCategoryViewHolder>() {

    var mentorCategoryList = mutableListOf<MentorCategory>()

    inner class MentorCategoryViewHolder(val binding: ItemMenteeHomeCategoryBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(currentMentorCategory: MentorCategory, position: Int) {
            binding.mentorCategory = currentMentorCategory

            val innerMentorRVAdapter = MentorPostRVAdapter()
            innerMentorRVAdapter.mentorList = currentMentorCategory.mentorPost

            binding.menteeHomeCategoryMentorRv.adapter = innerMentorRVAdapter

            setMentorMoreClickListener(position)
        }

        private fun setMentorMoreClickListener(position: Int) {
            binding.menteeHomeCategoryMoreImg.setOnClickListener {
                it.navigateWithData(HomeFragmentDirections.actionHomeFragmentToSearchFragment(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorCategoryViewHolder {
        val binding = ItemMenteeHomeCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MentorCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorCategoryViewHolder, position: Int) {
        holder.bind(mentorCategoryList[position],position)
    }

    override fun getItemCount(): Int {
        return mentorCategoryList.size
    }

}