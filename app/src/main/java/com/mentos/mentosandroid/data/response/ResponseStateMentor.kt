package com.mentos.mentosandroid.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseStateMentor(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: StateMentorResult
)

data class StateMentorResult(
    val beforeMentoring: List<StateNow>,
    val nowMentoring: List<StateNow>,
    val endMentoring: List<StateEndMentor>
)

@Parcelize
data class StateNow(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    // val mentoringCount2: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String,
) : Parcelable


@Parcelize
data class StateEndMentor(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    // val mentoringCount2: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String,
    // val mentoringCreateAt: String,
    // val mentoringUpdateAt: String
    //  val mentoringStatus: Int,
) : Parcelable
