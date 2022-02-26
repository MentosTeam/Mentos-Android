package com.mentos.mentosandroid.ui.chat

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
import java.util.*
import kotlin.collections.HashMap

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
        readChatDB()
        setNewMsgEtObserve()
        setChatBubbleListObserve()
        return binding.root
    }

    private fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.setDecorFitsSystemWindows(false)
            ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
                val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                binding.root.setPadding(0, 0, 0, imeHeight)
                insets
            }
        } else {
            activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
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

    private fun setSendBtnClickListener() {
        val currentTime = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("MM/dd HH:mm", Locale.KOREA)
        val getTime: String = dateFormat.format(currentTime)

        binding.chatBtnSendIv.setOnClickListener {

            if (chatViewModel.chatRoomKey.value == null) {
                val users = HashMap<String, Boolean>()
                users[SharedPreferenceController.getMemberId().toString()] = true
                users[args.memberId] = true

                database.child("chatRooms").push().setValue(
                    ChatModel(
                        users = users,
                        comments = null
                    )
                ).addOnSuccessListener {
                    readChatDB()
                }
            }

            Handler(Looper.getMainLooper()).postDelayed({
                if (chatViewModel.newMsg.value!!.isNotEmpty()) {
                    database.child("chatRooms")
                        .child(chatViewModel.chatRoomKey.value.toString())
                        .child("comments").push().setValue(
                            ChatBubble(
                                memberId = SharedPreferenceController.getMemberId().toString(),
                                content = requireNotNull(chatViewModel.newMsg.value),
                                createAt = getTime
                            )
                        ).addOnCompleteListener {
                            chatViewModel.newMsg.value = ""
                            if (chatViewModel.chatBubbleList.value != null) {
                                binding.chatBubbleRv.scrollToPosition(chatViewModel.chatBubbleList.value!!.size - 1)
                            }
                        }
                }
            }, 100)
        }
    }

    private fun writeProfileDB() {
        database.child("profile").child(args.memberId).setValue(
            ChatProfile(
                memberId = args.memberId,
                profileImage = args.imageUrl,
                nickname = args.nickname
            )
        )
    }

    private fun readChatDB() {
        database.child("chatRooms")
            .orderByChild("users/${SharedPreferenceController.getMemberId()}").equalTo(true)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    chatViewModel.clearChatBubbleList()

                    for (chatSnapshot in dataSnapshot.children) {
                        val chatItem = chatSnapshot.getValue(ChatModel::class.java)
                        if (chatItem!!.users.containsKey(args.memberId)) {
                            chatViewModel.setRoomKey(chatSnapshot.key.toString())
                            for (bubbleSnapShot in chatSnapshot.child("comments").children) {

                                val bubbleItem = bubbleSnapShot.getValue(ChatBubble::class.java)
                                chatViewModel.addChatBubbleList(requireNotNull(bubbleItem))

                                // 읽음표시
                                if (chatViewModel.chatBubbleList.value != null &&
                                    !chatViewModel.chatBubbleList.value?.get(chatViewModel.chatBubbleList.value!!.size - 1)?.readUsers!!.containsKey(
                                        SharedPreferenceController.getMemberId().toString())
                                ) {
                                    val bubbleUpdateItem =
                                        bubbleSnapShot.getValue(ChatBubble::class.java)

                                    bubbleUpdateItem!!.readUsers[SharedPreferenceController.getMemberId().toString()] = true
                                    val chatItemKey = bubbleSnapShot.key.toString()
                                    val updateReadUserMap: MutableMap<String, ChatBubble> =
                                        mutableMapOf()
                                    updateReadUserMap[chatItemKey] = bubbleUpdateItem
                                    database.child("chatRooms")
                                        .child(chatViewModel.chatRoomKey.value.toString())
                                        .child("comments")
                                        .updateChildren(updateReadUserMap as Map<String, ChatBubble>)
                                }

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
        binding.chatBubbleRv.itemAnimator = null
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
                chatViewModel.postReport(3, memberId.toInt(), reportText)
                chatViewModel.isSuccessReport.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess) {
                        OneButtonDialog(5) {

                        }.show(childFragmentManager, "report")
                    }
                }
            }.show(childFragmentManager, "report_text")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    }
}