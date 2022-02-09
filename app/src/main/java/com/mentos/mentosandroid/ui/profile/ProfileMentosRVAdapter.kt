package com.mentos.mentosandroid.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.databinding.ItemMentosBinding
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg37

class ProfileMentosRVAdapter : RecyclerView.Adapter<ProfileMentosRVAdapter.MentosViewHolder>() {

    var mentosList = mutableListOf<Int>()

    inner class MentosViewHolder(val binding: ItemMentosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentMentos: Int) {
            binding.itemMentosImg.setMentosImg37(currentMentos)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentosViewHolder {
        val binding =
            ItemMentosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MentosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MentosViewHolder, position: Int) {
        holder.bind(mentosList[position])
    }

    override fun getItemCount(): Int {
        return mentosList.size
    }
}