package com.mentos.mentosandroid.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.ChatList
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.databinding.ItemChatListBinding
import com.mentos.mentosandroid.util.navigateWithData

class ChatListAdapter(val fragment: Fragment) :
    ListAdapter<ChatList, ChatListAdapter.ChatListViewHolder>(ChatListDiffUtil()) {

    inner class ChatListViewHolder(
        private val binding: ItemChatListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatList) {
            binding.data = item
            if (item.profile.profileImage!!.isEmpty()) {
                binding.itemChatListImg.setImageResource(R.drawable.img_default_mentos)
            } else {
                Glide.with(fragment)
                    .load(item.profile.profileImage)
                    .into(binding.itemChatListImg)
            }

            if (item.comments.content != "") {
                val time = item.comments.createAt.split(" ")
                binding.itemChatListDateTv.text = time[0]

                if (item.comments.memberId != SharedPreferenceController.getMemberId().toString()
                    && item.comments.readUsers.size != 2
                ) {
                    binding.chatListUpdate.visibility = View.VISIBLE
                } else {
                    binding.chatListUpdate.visibility = View.GONE
                }
            }

            binding.itemChatListLayout.setOnClickListener {
                it.navigateWithData(
                    ChatListFragmentDirections.actionChatListFragmentToChatRoomFragment(
                        nickname = item.profile.nickname,
                        memberId = item.profile.memberId,
                        imageUrl = item.profile.profileImage
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val binding: ItemChatListBinding =
            ItemChatListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ChatListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ChatListDiffUtil : DiffUtil.ItemCallback<ChatList>() {
        override fun areContentsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
            return oldItem.profile.memberId == newItem.profile.memberId
        }
    }
}