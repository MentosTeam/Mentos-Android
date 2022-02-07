package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.MenteeCategory
import com.mentos.mentosandroid.databinding.ItemMentorHomeCategoryBinding
import com.mentos.mentosandroid.util.navigateWithData

class MenteeCategoryRVAdapter(): RecyclerView.Adapter<MenteeCategoryRVAdapter.MenteeCategoryViewHolder>() {

    var menteeCategoryList = mutableListOf<MenteeCategory>()

    inner class MenteeCategoryViewHolder(val binding: ItemMentorHomeCategoryBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentMenteeCategory: MenteeCategory, position: Int){
            binding.menteeCategory = currentMenteeCategory

            val innerMenteeRVAdapter = MenteeRVAdapter()
            innerMenteeRVAdapter.menteeList = currentMenteeCategory.mentee

            binding.mentorHomeCategoryMenteeRv.adapter = innerMenteeRVAdapter

            setMenteeMoreClickListener(position)
        }

        private fun setMenteeMoreClickListener(position: Int) {
            binding.mentorHomeCategoryMoreImg.setOnClickListener {
                it.navigateWithData(HomeFragmentDirections.actionHomeFragmentToSearchFragment(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenteeCategoryViewHolder {
        val binding = ItemMentorHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenteeCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenteeCategoryViewHolder, position: Int) {
        holder.bind(menteeCategoryList[position], position)
    }

    override fun getItemCount(): Int {
        return menteeCategoryList.size
    }

}