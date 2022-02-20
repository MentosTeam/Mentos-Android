package com.mentos.mentosandroid.ui.notification

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.Notice

object NotificationBindingAdapter {
    @BindingAdapter("notificationItems")
    @JvmStatic
    fun setNotificationItems(recyclerView: RecyclerView, items: ArrayList<Notice>?) {
        if (items == null) {
            return
        }
        if (recyclerView.adapter == null) {
            val adapter = NotificationRVAdapter()
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter
        }
        val notificationRVAdapter = recyclerView.adapter as NotificationRVAdapter

        notificationRVAdapter.notiList = items
        notificationRVAdapter.notifyDataSetChanged()
    }
}