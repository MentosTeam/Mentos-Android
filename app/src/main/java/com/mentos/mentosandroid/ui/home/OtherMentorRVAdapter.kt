package com.mentos.mentosandroid.ui.home

import android.content.ContentProvider
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.OtherMentor
import com.mentos.mentosandroid.databinding.ItemHomeOtherMentorBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.getMentosText
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData

class OtherMentorRVAdapter() : RecyclerView.Adapter<OtherMentorRVAdapter.OtherMentorViewHolder>() {

    var otherMentorList = mutableListOf<OtherMentor>()

    inner class OtherMentorViewHolder(val binding: ItemHomeOtherMentorBinding, val context: Context) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(currentOtherMentor: OtherMentor) {
            binding.otherMentor = currentOtherMentor

            binding.itemHomeOtherMentorTv.text =
                currentOtherMentor.nickName + "/" + currentOtherMentor.mentorMajor + "/" + currentOtherMentor.mentorYear
            binding.itemHomeOtherMentorTagTv.text =
                "#" + getMentosText(currentOtherMentor.firstMajorCategory) +
                        ", #" + getMentosText(currentOtherMentor.secondMajorCategory)

            binding.itemHomeOtherMentorLayout.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToOneMentorProfileFragment(
                        currentOtherMentor.mentorStudentId
                    )
                )
            }

            initImg(currentOtherMentor)
        }

        private fun initImg(currentOtherMentor: OtherMentor) {
            if (currentOtherMentor.mentorImage == null) {
                binding.itemHomeOtherMentorImg.setImageResource(R.drawable.img_home_user)
            } else {
                Glide.with(context)
                    .load(currentOtherMentor.mentorImage)
                    .into(binding.itemHomeOtherMentorImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherMentorViewHolder {
        val binding =
            ItemHomeOtherMentorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherMentorViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: OtherMentorViewHolder, position: Int) {
        holder.bind(otherMentorList[position])
    }

    override fun getItemCount(): Int {
        return otherMentorList.size
    }

}