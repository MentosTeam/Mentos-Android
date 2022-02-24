package com.mentos.mentosandroid.data.local

data class ChatModel(
    val comments: ChatBubble? = ChatBubble(),
    val users: HashMap<String, Boolean> = HashMap()
)

data class ChatBubble(
    val memberId: String = "",
    val content: String = "",
    val createAt: String = "",
    val readUsers: HashMap<String, Boolean> = HashMap()
)