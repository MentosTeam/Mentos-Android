package com.mentos.mentosandroid.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.response.MentorPost
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.databinding.ItemHomeMentorPostBinding
import com.mentos.mentosandroid.util.navigateWithData

class MentorPostRVAdapter : RecyclerView.Adapter<MentorPostRVAdapter.MentorPostViewHolder>() {

    var mentorList = mutableListOf<MentorPost>()

    inner class MentorPostViewHolder(val binding: ItemHomeMentorPostBinding, val context: Context) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(currentMentorPost: MentorPost) {
            binding.mentorPost = currentMentorPost

            binding.itemHomeMentorLayout.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToSearchDetailDialog(
                        postMento = SearchMentor(
                            currentMentorPost.postImgUrl.toString(),
                            currentMentorPost.postCategoryId,
                            currentMentorPost.mentorMajor,
                            currentMentorPost.mentorStudentId,
                            currentMentorPost.nickName,
                            currentMentorPost.postContents,
                            currentMentorPost.postId,
                            currentMentorPost.postTitle,
                            currentMentorPost.mentorImage
                        )
                    )
                )
            }

            initImg(currentMentorPost)
        }

        private fun initImg(currentMentorPost: MentorPost) {
            if (currentMentorPost.mentorImage == null) {
                binding.itemHomeMentorImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                Glide.with(context)
                    .load(currentMentorPost.mentorImage)
                    .into(binding.itemHomeMentorImg)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorPostViewHolder {
        val binding =
            ItemHomeMentorPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorPostViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MentorPostViewHolder, position: Int) {
        holder.bind(mentorList[position])
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }

}