package com.mentos.mentosandroid.data.response

data class ResponseNotice(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<Notice>
)

data class Notice(
    val content: String,
    val createAt: String,
    val noticeId: Int
)