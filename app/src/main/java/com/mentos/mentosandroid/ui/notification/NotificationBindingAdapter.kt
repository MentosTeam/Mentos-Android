package com.mentos.mentosandroid.ui.notification

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.*

object NotificationBindingAdapter {
    @BindingAdapter("notificationItems")
    @JvmStatic
    fun setNotificationItems(recyclerView: RecyclerView, items: ArrayList<Notification>) {
        if (recyclerView.adapter == null) {
            val adapter = NotificationRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val notificationRVAdapter = recyclerView.adapter as NotificationRVAdapter

        notificationRVAdapter.notiList = items
        notificationRVAdapter.notifyDataSetChanged()
    }
}