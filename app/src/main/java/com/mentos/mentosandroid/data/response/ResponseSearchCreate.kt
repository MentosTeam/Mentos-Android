package com.mentos.mentosandroid.data.response

data class ResponseSearchCreate(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SearchPostId
)

data class SearchPostId(
    val postId: Int
)