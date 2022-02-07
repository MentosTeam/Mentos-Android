package com.mentos.mentosandroid.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseSearchMentor(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: SearchMentorResult
)

data class SearchMentorResult(
    val postArr: List<SearchMentor>
)

@Parcelize
data class SearchMentor(
    val imageUrl: String?,
    val majorCategoryId: Int,
    val memberMajor: String,
    val mentoId: Int,
    val mentoNickName: String,
    val postContents: String,
    val postId: Int,
    val postTitle: String
) : Parcelable