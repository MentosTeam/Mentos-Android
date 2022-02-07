package com.mentos.mentosandroid.data.request

data class RequestSignUp(
    val memberEmail: String,
    val memberName: String,
    val memberNickName: String,
    val memberPw: String,
    val memberSchoolName: String,
    val memberSex: String,
    val memberStudentId: Int
)