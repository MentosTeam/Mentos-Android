package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.MentorPost
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.databinding.ItemHomeMentorPostBinding
import com.mentos.mentosandroid.util.navigateWithData

class MentorPostRVAdapter : RecyclerView.Adapter<MentorPostRVAdapter.MentorPostViewHolder>() {

    var mentorList = mutableListOf<MentorPost>()

    inner class MentorPostViewHolder(val binding: ItemHomeMentorPostBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {
        fun bind(currentMentorPost: MentorPost) {
            binding.mentorPost = currentMentorPost

            //글 상세보기 다이얼로그 이동
            binding.itemHomeMentorLayout.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToSearchDetailDialog(
                        myList = false,
                        postMento = Search(
                            currentMentorPost.postTitle,
                            currentMentorPost.postCategoryId,
                            currentMentorPost.mentorStudentId,
                            currentMentorPost.postImgUrl,
                            currentMentorPost.nickName,
                            currentMentorPost.postContents,
                            currentMentorPost.postId
                        )
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorPostViewHolder {
        val binding =
            ItemHomeMentorPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentorPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentorPostViewHolder, position: Int) {
        holder.bind(mentorList[position])
    }

    override fun getItemCount(): Int {
        return mentorList.size
    }

}