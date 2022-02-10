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

//@Parcelize
//data class SearchMentee(
//    val mentiId: Int,
//    val mentiImage: String,
//    val mentiIntro: String,
//    val mentiMajorFirst: Int,
//    val mentiMajorSecond: Int,
//    val mentiNickName: String
//) : Parcelable