package com.mentos.mentosandroid.data.response

data class ResponseSearchCategory(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SearchCategory
)

data class SearchCategory(
    val first: Int,
    val second: Int
)