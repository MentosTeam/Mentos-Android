package com.mentos.mentosandroid.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.Notification
import com.mentos.mentosandroid.databinding.ItemNotificationBinding

class NotificationRVAdapter : RecyclerView.Adapter<NotificationRVAdapter.NotificationViewHolder>() {

    var notiList = mutableListOf<Notification>()

    inner class NotificationViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentNoti: Notification) {
            binding.notification = currentNoti
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val binding =
            ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notiList[position])
    }

    override fun getItemCount(): Int {
        return notiList.size
    }


}