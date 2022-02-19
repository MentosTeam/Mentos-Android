package com.mentos.mentosandroid.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentChatListBinding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

class ChatListFragment : Fragment() {
    private lateinit var binding: FragmentChatListBinding
    private val chatViewModel by viewModels<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatListBinding.inflate(layoutInflater, container, false)
        setBackBtnClickListener()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.chatListBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }
}