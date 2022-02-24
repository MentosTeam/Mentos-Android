package com.mentos.mentosandroid.data.local

data class ChatList(
    val profile: ChatProfile = ChatProfile(),
    val comments: ChatBubble = ChatBubble(),
)

data class ChatProfile(
    val profileImage: String? = "",
    val memberId: String = "",
    val nickname: String = ""
)