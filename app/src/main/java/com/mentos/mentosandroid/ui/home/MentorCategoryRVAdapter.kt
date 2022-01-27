package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.MentorCategory
import com.mentos.mentosandroid.databinding.ItemMenteeHomeCategoryBinding
import com.mentos.mentosandroid.util.navigate

class MentorCategoryRVAdapter(): RecyclerView.Adapter<MentorCategoryRVAdapter.MentorCategoryViewHolder>() {

    var mentorCategoryList = mutableListOf<MentorCategory>()

    inner class MentorCategoryViewHolder(val binding: ItemMenteeHomeCategoryBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentMentorCategory: MentorCategory){
            binding.mentorCategory = currentMentorCategory

            val innerMentorRVAdapter = MentorRVAdapter()
            innerMentorRVAdapter.mentorList = currentMentorCategory.mentorList

            binding.menteeHomeCategoryMentorRv.adapter = innerMentorRVAdapter

            //더보기 이미지 클릭 시 멘토스 찾기 화면으로 이동
            setMentorMoreClickListener()
        }

        private fun setMentorMoreClickListener() {
            binding.menteeHomeCategoryMoreImg.setOnClickListener {
                it.navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorCategoryViewHolder {
        val binding = ItemMenteeHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorCategoryViewHolder, position: Int) {
        holder.bind(mentorCategoryList[position])
    }

    override fun getItemCount(): Int {
        return mentorCategoryList.size
    }

}