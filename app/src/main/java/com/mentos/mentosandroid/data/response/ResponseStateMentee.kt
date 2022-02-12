package com.mentos.mentosandroid.data.response

data class ResponseStateMentee(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: StateMenteeResult
)

data class StateMenteeResult(
    val waitMentoring: List<StateWait>,
    val nowMentoring: List<StateNow>,
    val endMentoring: List<StateEnd>
)
