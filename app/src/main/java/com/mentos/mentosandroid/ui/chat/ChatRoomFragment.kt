package com.mentos.mentosandroid.ui.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.local.*
import com.mentos.mentosandroid.databinding.FragmentChatRoomBinding
import com.mentos.mentosandroid.util.KeyBoardUtil
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.popBackStack
import java.text.SimpleDateFormat

class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentChatRoomBinding
    private lateinit var database: DatabaseReference
    private val chatViewModel by viewModels<ChatViewModel>()
    private val args by navArgs<ChatRoomFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatRoomBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = chatViewModel
        database = Firebase.database.reference
        initView()
        setBackBtnClickListener()
        setEtLayoutClickListener()
        setSendBtnClickListener()
        setReportBtnClickListener()
        setAdapter()
        writeProfileDB()
        setNewMsgEtObserve()
        setChatBubbleListObserve()
        return binding.root
    }

    private fun initView() {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        binding.chatRoomTitleTv.text = args.nickname
    }

    private fun setBackBtnClickListener() {
        binding.chatRoomBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setEtLayoutClickListener() {
        binding.chatSendLayout.setOnClickListener {
            KeyBoardUtil.show(requireContext(), binding.chatSendEt)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setSendBtnClickListener() {
        val currentTime = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MM/dd HH:mm")
        val getTime: String = dateFormat.format(currentTime)

        binding.chatBtnSendIv.setOnClickListener {
            database.child("chatRooms")
                .child(chatViewModel.chatRoomKey.value.toString())
                .child("comments").push().setValue(
                    ChatBubble(
                        memberId = SharedPreferenceController.getMemberId()
                            .toString(),
                        content = requireNotNull(chatViewModel.newMsg.value),
                        createAt = getTime
                    )
                )

            chatViewModel.newMsg.value = ""
            binding.chatBubbleRv.itemAnimator = null
            if (chatViewModel.chatBubbleList.value != null) {
                binding.chatBubbleRv.scrollToPosition(chatViewModel.chatBubbleList.value!!.size - 1)
            }
        }
    }

    private fun writeProfileDB() {
        database.child("profile").child(args.memberId.toString()).setValue(
            ChatProfile(
                memberId = args.memberId.toString(),
                profileImage = args.imageUrl,
                nickname = args.nickname
            )
        )
        readChatDB()

        Handler(Looper.getMainLooper()).postDelayed({
            if (chatViewModel.chatRoomKey.value == null) {

                val users = HashMap<String, Boolean>()
                users[SharedPreferenceController.getMemberId().toString()] = true
                users[args.memberId.toString()] = true

                database.child("chatRooms").push().setValue(
                    ChatModel(
                        users = users,
                        comments = null
                    )
                ).addOnSuccessListener {
                    readChatDB()
                }
            }
        }, 2000)
    }

    private fun readChatDB() {
        database.child("chatRooms")
            .orderByChild("users/${SharedPreferenceController.getMemberId()}")
            .equalTo(true)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    chatViewModel.clearChatBubbleList()

                    for (chatSnapshot in dataSnapshot.children) {
                        val chatItem = chatSnapshot.getValue(ChatModel::class.java)
                        if (chatItem!!.users.containsKey(args.memberId.toString())) {
                            chatViewModel.setRoomKey(chatSnapshot.key.toString())
                            for (chatBubbleSnapShot in chatSnapshot.child("comments").children) {
                                val bubbleItem = chatBubbleSnapShot.getValue(ChatBubble::class.java)
                                chatViewModel.addChatBubbleList(requireNotNull(bubbleItem))
                            }
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("read from FireBase", error.toException().toString())
                }
            })
    }

    private fun setAdapter() {
        binding.chatBubbleRv.adapter = ChatBubbleAdapter()
    }

    private fun setChatBubbleListObserve() {
        chatViewModel.chatBubbleList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.chatBubbleRv.adapter as ChatBubbleAdapter) {
                    submitList(list)
                }
            }
        }
    }

    private fun setNewMsgEtObserve() {
        chatViewModel.newMsg.observe(viewLifecycleOwner) {
            when (it.length) {
                0 -> {
                    binding.chatBtnSendIv.setImageResource(R.drawable.ic_chat_send_unactive)
                    binding.chatBtnSendIv.isClickable = false
                }
                else -> {
                    binding.chatBtnSendIv.setImageResource(R.drawable.ic_chat_send_active)
                    binding.chatBtnSendIv.isClickable = true
                }
            }
        }
    }

    private fun setReportBtnClickListener() {
        binding.chatRoomBtnReportIb.setOnClickListener {
            EditTextDialog(2) { reportText ->
                val memberId = args.memberId
                chatViewModel.postReport(3, memberId, reportText)
                chatViewModel.isSuccessReport.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess) {
                        OneButtonDialog(5) {

                        }.show(childFragmentManager, "report")
                    }
                }
            }.show(childFragmentManager, "report_text")
        }
    }
}