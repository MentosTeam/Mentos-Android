package com.mentos.mentosandroid.data.response

data class ResponseSearchMentee(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SearchMenteeResult
)

data class SearchMenteeResult(
    val mentiArr: List<Mentee>
)