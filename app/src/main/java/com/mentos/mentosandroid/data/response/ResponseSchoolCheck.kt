package com.mentos.mentosandroid.data.response

data class ResponseSchoolCheck(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SchoolCheckNum
)

data class SchoolCheckNum(
    val certificationNumber: String
)