package com.mentos.mentosandroid.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import com.mentos.mentosandroid.data.local.*
import com.mentos.mentosandroid.databinding.FragmentChatListBinding
import com.mentos.mentosandroid.util.popBackStack
import java.util.*

class ChatListFragment : Fragment() {
    private lateinit var binding: FragmentChatListBinding
    private lateinit var database: DatabaseReference
    private val chatViewModel by viewModels<ChatViewModel>()
    private val myId = SharedPreferenceController.getMemberId().toString()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(layoutInflater, container, false)
        setBackBtnClickListener()
        database = Firebase.database.reference
        setAdapter()
        setChatListObserve()
        readMyChatList()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.chatListBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setAdapter() {
        binding.chatListRv.adapter = ChatListAdapter(this@ChatListFragment)
    }

    private fun setChatListObserve() {
        chatViewModel.chatList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.chatListRv.adapter as ChatListAdapter) { submitList(list) }
            }
        }
    }

    private fun readMyChatList() {
        database.child("chatRooms")
            .orderByChild("users/${myId}").equalTo(true)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    chatViewModel.clearChatList()
                    for (chatSnapshot in snapshot.children) {
                        val chatItem = chatSnapshot.getValue(ChatModel::class.java)

                        for (memberId in chatItem!!.users) {
                            if (memberId.key != myId) {
                                val comment: MutableList<ChatBubble> = mutableListOf()

                                for (chatBubbleSnapShot in chatSnapshot.child("comments").children) {
                                    val bubbleItem =
                                        chatBubbleSnapShot.getValue(ChatBubble::class.java)
                                    comment.add(requireNotNull(bubbleItem))
                                }
                                comment.reverse()

                                database.child("profile").child(memberId.key)
                                    .addListenerForSingleValueEvent(object : ValueEventListener {
                                        override fun onDataChange(snapshot: DataSnapshot) {
                                            val item = snapshot.getValue(ChatProfile::class.java)
                                            val profileItem = ChatProfile(
                                                memberId = item!!.memberId,
                                                nickname = item.nickname,
                                                profileImage = item.profileImage
                                            )
                                            val chatListItem = if (comment.size != 0) {
                                                ChatList(
                                                    profileItem,
                                                    comment[0].content,
                                                    comment[0].createAt
                                                )
                                            } else {
                                                ChatList(
                                                    profileItem,
                                                    "",
                                                    ""
                                                )
                                            }
                                            chatViewModel.addChatList(chatListItem)
                                        }

                                        override fun onCancelled(error: DatabaseError) {
                                        }
                                    })
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("read from FireBase", error.toException().toString())
                }
            })
    }
}