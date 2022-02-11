package com.mentos.mentosandroid.data.response

data class ResponseMyPostList(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ArrayList<SearchMentor>
)