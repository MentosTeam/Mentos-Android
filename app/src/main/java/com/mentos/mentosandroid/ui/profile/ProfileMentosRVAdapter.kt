package com.mentos.mentosandroid.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.databinding.ItemMajorMentosBinding
import com.mentos.mentosandroid.databinding.ItemMentosBinding
import com.mentos.mentosandroid.util.setMentosImg37
import com.mentos.mentosandroid.util.setMentosImg41
import com.mentos.mentosandroid.util.setMentosText

class ProfileMentosRVAdapter : RecyclerView.Adapter<ProfileMentosRVAdapter.MentosViewHolder>() {

    var mentosList = mutableListOf<Int>(10)

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