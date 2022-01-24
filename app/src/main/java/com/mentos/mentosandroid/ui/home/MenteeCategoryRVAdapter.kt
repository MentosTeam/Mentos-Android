package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.MenteeCategory
import com.mentos.mentosandroid.databinding.ItemMentorHomeCategoryBinding

class MenteeCategoryRVAdapter(): RecyclerView.Adapter<MenteeCategoryRVAdapter.MenteeCategoryViewHolder>() {

    var menteeCategoryList = mutableListOf<MenteeCategory>()

    inner class MenteeCategoryViewHolder(val binding: ItemMentorHomeCategoryBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentMenteeCategory: MenteeCategory){
            binding.menteeCategory = currentMenteeCategory

            val innerProfileRVAdapter = ProfileRVAdapter()
            innerProfileRVAdapter.profileList = currentMenteeCategory.menteeList

            binding.mentorHomeCategoryMenteeRv.adapter = innerProfileRVAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenteeCategoryViewHolder {
        val binding = ItemMentorHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenteeCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenteeCategoryViewHolder, position: Int) {
        holder.bind(menteeCategoryList[position])
    }

    override fun getItemCount(): Int {
        return menteeCategoryList.size
    }

}