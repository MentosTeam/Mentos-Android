package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Mentor
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.databinding.ItemHomeMentorBinding
import com.mentos.mentosandroid.ui.search.SearchFragmentDirections
import com.mentos.mentosandroid.util.navigateWithData

class MentorRVAdapter: RecyclerView.Adapter<MentorRVAdapter.MentorViewHolder>() {

    var mentorList = mutableListOf<Mentor>()

    inner class MentorViewHolder(val binding: ItemHomeMentorBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentMentor: Mentor){
            binding.mentor = currentMentor

            //글 상세보기 다이얼로그 이동
            binding.itemHomeMentorLayout.setOnClickListener {
                it.navigateWithData(
                    HomeFragmentDirections.actionHomeFragmentToSearchDetailDialog(
                        Search(
                            currentMentor.title, 1,
                            1,null, "홍길동", "본문", 1
                        )
                    )
                )
            }
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