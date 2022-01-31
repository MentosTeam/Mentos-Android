package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Mentee
import com.mentos.mentosandroid.databinding.ItemHomeMenteeBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.geMentosText

class MenteeRVAdapter() : RecyclerView.Adapter<MenteeRVAdapter.MenteeViewHolder>() {

    var menteeList = mutableListOf<Mentee>()

    inner class MenteeViewHolder(val binding: ItemHomeMenteeBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(currentMentee: Mentee) {
            binding.mentee = currentMentee

            binding.itemHomeMenteeTv.text =
                currentMentee.nickName + "/" + currentMentee.menteeMajor + "/" + currentMentee.mentorYear
            binding.itemHomeMenteeTagTv.text =
                "#" + geMentosText(currentMentee.firstMajorCategory) +
                        ", #" + geMentosText(currentMentee.secondMajorCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenteeViewHolder {
        val binding =
            ItemHomeMenteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenteeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenteeViewHolder, position: Int) {
        holder.bind(menteeList[position])
    }

    override fun getItemCount(): Int {
        return menteeList.size
    }

}