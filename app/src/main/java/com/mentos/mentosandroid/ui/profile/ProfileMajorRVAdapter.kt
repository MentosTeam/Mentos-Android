package com.mentos.mentosandroid.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.databinding.ItemMajorMentosBinding
import com.mentos.mentosandroid.util.setMentosImg41
import com.mentos.mentosandroid.util.setMentosText

class ProfileMajorRVAdapter : RecyclerView.Adapter<ProfileMajorRVAdapter.MajorViewHolder>() {

    var majorList = mutableListOf<Int>()

    inner class MajorViewHolder(val binding: ItemMajorMentosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMajor: Int) {
            binding.itemMajorImg.setMentosImg41(currentMajor)
            binding.itemMajorTv.setMentosText(currentMajor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MajorViewHolder {
        val binding =
            ItemMajorMentosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MajorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MajorViewHolder, position: Int) {
        holder.bind(majorList[position])
    }

    override fun getItemCount(): Int {
        return majorList.size
    }
}