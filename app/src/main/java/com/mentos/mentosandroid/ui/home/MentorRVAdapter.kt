package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Mentor
import com.mentos.mentosandroid.databinding.ItemHomeMentorBinding

class MentorRVAdapter: RecyclerView.Adapter<MentorRVAdapter.MentorViewHolder>() {

    var mentorList = mutableListOf<Mentor>()

    inner class MentorViewHolder(val binding: ItemHomeMentorBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentMentor: Mentor){
            binding.mentor = currentMentor
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorViewHolder {
        val binding = ItemHomeMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorViewHolder, position: Int) {
        holder.bind(mentorList[position])
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }

}