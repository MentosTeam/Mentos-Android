package com.mentos.mentosandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Profile
import com.mentos.mentosandroid.databinding.ItemHomeProfileBinding

class ProfileRVAdapter(): RecyclerView.Adapter<ProfileRVAdapter.ProfileViewHolder>() {

    var profileList = mutableListOf<Profile>()

    inner class ProfileViewHolder(val binding: ItemHomeProfileBinding) : RecyclerView.ViewHolder(
        binding.root){
        fun bind(currentProfile: Profile){
            binding.profile = currentProfile
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding = ItemHomeProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(profileList[position])
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

}