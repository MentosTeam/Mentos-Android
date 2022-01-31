package com.mentos.mentosandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.OtherMentor
import com.mentos.mentosandroid.databinding.ItemHomeOtherMentorBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.geMentosText
import com.mentos.mentosandroid.util.navigate

class OtherMentorRVAdapter() : RecyclerView.Adapter<OtherMentorRVAdapter.OtherMentorViewHolder>() {

    var otherMentorList = mutableListOf<OtherMentor>()

    inner class OtherMentorViewHolder(val binding: ItemHomeOtherMentorBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(currentOtherMentor: OtherMentor) {
            binding.otherMentor = currentOtherMentor

            binding.itemHomeOtherMentorTv.text =
                currentOtherMentor.nickName + "/" + currentOtherMentor.mentorMajor + "/" + currentOtherMentor.mentorYear
            binding.itemHomeOtherMentorTagTv.text =
                "#" + geMentosText(currentOtherMentor.firstMajorCategory) +
                        ", #" + geMentosText(currentOtherMentor.secondMajorCategory)

            binding.itemHomeOtherMentorLayout.setOnClickListener {
                it.navigate(R.id.action_homeFragment_to_oneMentorProfileFragment)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherMentorViewHolder {
        val binding =
            ItemHomeOtherMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherMentorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OtherMentorViewHolder, position: Int) {
        holder.bind(otherMentorList[position])
    }

    override fun getItemCount(): Int {
        return otherMentorList.size
    }

}