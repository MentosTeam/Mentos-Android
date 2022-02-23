package com.mentos.mentosandroid.data.local

data class ChatList(
    val profile: ChatProfile = ChatProfile(),
    val lastMsg: String? = "",
    val date: String? = "",
)

data class ChatProfile(
    val profileImage: String? = "",
    val memberId: String = "",
    val nickname: String = ""
)