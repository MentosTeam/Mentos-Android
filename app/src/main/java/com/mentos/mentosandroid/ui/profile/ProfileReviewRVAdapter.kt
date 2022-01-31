package com.mentos.mentosandroid.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.databinding.ItemProfileReviewBinding

class ProfileReviewRVAdapter :
    RecyclerView.Adapter<ProfileReviewRVAdapter.ReviewViewHolder>() {

    var reviewList = mutableListOf<String>()

    inner class ReviewViewHolder(
        val binding: ItemProfileReviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currentReview: String) {
            binding.review = currentReview

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding =
            ItemProfileReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewList[position])
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}