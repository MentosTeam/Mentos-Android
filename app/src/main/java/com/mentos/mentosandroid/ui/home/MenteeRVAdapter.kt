package com.mentos.mentosandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.data.response.MentorPost
import com.mentos.mentosandroid.databinding.ItemHomeMenteeBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.getMentosText
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class MenteeRVAdapter() : RecyclerView.Adapter<MenteeRVAdapter.MenteeViewHolder>() {

    var menteeList = mutableListOf<Mentee>()

    inner class MenteeViewHolder(val binding: ItemHomeMenteeBinding, val context: Context) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(currentMentee: Mentee) {
            binding.mentee = currentMentee

            binding.itemHomeMenteeTv.text =
                currentMentee.nickName + "/" + currentMentee.menteeMajor + "/" + currentMentee.menteeYear
            binding.itemHomeMenteeTagTv.text =
                "#" + getMentosText(currentMentee.firstMajorCategory) +
                        ", #" + getMentosText(currentMentee.secondMajorCategory)


            binding.itemHomeMenteeLayout.setOnClickListener {
                it.navigateWithData(HomeFragmentDirections.actionHomeFragmentToOneMenteeProfileFragment(currentMentee.menteeStudentId))
            }
            initImg(currentMentee)
        }

        private fun initImg(currentMentee: Mentee) {
            if (currentMentee.menteeImage == null) {
                binding.itemHomeMenteeImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                Glide.with(context)
                    .load(currentMentee.menteeImage)
                    .into(binding.itemHomeMenteeImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenteeViewHolder {
        val binding =
            ItemHomeMenteeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenteeViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MenteeViewHolder, position: Int) {
        holder.bind(menteeList[position])
    }

    override fun getItemCount(): Int {
        return menteeList.size
    }

}