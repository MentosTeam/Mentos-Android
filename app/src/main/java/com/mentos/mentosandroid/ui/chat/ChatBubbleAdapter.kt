package com.mentos.mentosandroid.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.ChatBubble
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.databinding.ItemChatBubbleBinding
import com.mentos.mentosandroid.databinding.ItemStateNowBinding

class ChatBubbleAdapter :
    ListAdapter<ChatBubble, ChatBubbleAdapter.ChatBubbleViewHolder>(ChatBubbleDiffUtil()) {

    inner class ChatBubbleViewHolder(
        private val binding: ItemChatBubbleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatBubble) {
            binding.chatContent = item.content
            val time = item.createAt.split(" ")
            binding.chatBubbleTimeTv.text = time[1]

            if (SharedPreferenceController.getMemberId().toString() == item.memberId) {
                binding.chatBubbleContentTv.setBackgroundResource(R.drawable.img_chat_mine)
                binding.itemChatBubbleLayout.layoutDirection = View.LAYOUT_DIRECTION_RTL
            } else {
                binding.chatBubbleContentTv.setBackgroundResource(R.drawable.img_chat_other)
                binding.itemChatBubbleLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBubbleViewHolder {
        val binding: ItemChatBubbleBinding =
            ItemChatBubbleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ChatBubbleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatBubbleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ChatBubbleDiffUtil : DiffUtil.ItemCallback<ChatBubble>() {
        override fun areContentsTheSame(oldItem: ChatBubble, newItem: ChatBubble): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ChatBubble, newItem: ChatBubble): Boolean {
            return oldItem.memberId == newItem.memberId
        }
    }
}