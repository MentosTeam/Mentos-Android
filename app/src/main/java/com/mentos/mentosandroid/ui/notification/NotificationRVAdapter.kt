package com.mentos.mentosandroid.ui.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.Notice
import com.mentos.mentosandroid.databinding.ItemNotificationBinding
import java.text.SimpleDateFormat
import java.util.*

class NotificationRVAdapter : RecyclerView.Adapter<NotificationRVAdapter.NotificationViewHolder>() {

    var notiList = mutableListOf<Notice>()

    inner class NotificationViewHolder(val binding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currentNoti: Notice) {
            binding.notification = currentNoti

            val date = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).parse(currentNoti.createAt.substring(0, 10))
            val sdf = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA)
            binding.itemNotificationDateTv.text = sdf.format(requireNotNull(date))
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