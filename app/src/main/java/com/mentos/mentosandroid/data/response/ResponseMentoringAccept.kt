package com.mentos.mentosandroid.data.response

data class ResponseMentoringAccept(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: MentoringAcceptResult
)

data class MentoringAcceptResult(
    val mentoId: Int,
    val mentoringId: Int,
    val status: String
)