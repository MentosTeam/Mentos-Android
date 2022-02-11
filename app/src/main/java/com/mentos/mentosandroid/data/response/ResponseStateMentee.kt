package com.mentos.mentosandroid.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseStateMentee(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: StateMenteeResult
)

data class StateMenteeResult(
    val beforeMentoring: List<StateNow>,
    val nowMentoring: List<StateNow>,
    val endMentoring: List<StateEndMentee>
)

@Parcelize
data class StateEndMentee(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    // val mentoringCount2: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String,
    val review: Boolean,
    // val mentoringCreateAt: String,
    // val mentoringUpdateAt: String
    //  val mentoringStatus: Int,
) : Parcelable
