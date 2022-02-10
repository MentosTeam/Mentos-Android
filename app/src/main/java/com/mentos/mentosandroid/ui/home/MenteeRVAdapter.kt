package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.databinding.ItemHomeMenteeBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.getMentosText
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class MenteeRVAdapter() : RecyclerView.Adapter<MenteeRVAdapter.MenteeViewHolder>() {

    var menteeList = mutableListOf<Mentee>()

    inner class MenteeViewHolder(val binding: ItemHomeMenteeBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(currentMentee: Mentee) {
            binding.mentee = currentMentee

            binding.itemHomeMenteeTv.text =
                currentMentee.nickName + "/" + currentMentee.menteeMajor + "/" + currentMentee.menteeYear + "학번"
            binding.itemHomeMenteeTagTv.text =
                "#" + getMentosText(currentMentee.firstMajorCategory) +
                        ", #" + getMentosText(currentMentee.secondMajorCategory)


            binding.itemHomeMenteeLayout.setOnClickListener {
                it.navigateWithData(HomeFragmentDirections.actionHomeFragmentToOneMenteeProfileFragment(currentMentee.menteeStudentId))
            }
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