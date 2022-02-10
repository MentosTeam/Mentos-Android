package com.mentos.mentosandroid.data.response

data class ResponseMentoringStart(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ResultMentoringStart
)

data class ResultMentoringStart(
    val mentiId: Int,
    val mentoId: Int,
    val mentoringId: Int
)