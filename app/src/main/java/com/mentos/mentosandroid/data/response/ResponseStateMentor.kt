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
    val waitMentoring: List<StateWait>,
    val nowMentoring: List<StateNow>,
    val endMentoring: List<StateEnd>
)

@Parcelize
data class StateNow(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    val currentCount: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String
) : Parcelable

@Parcelize
data class StateEnd(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String,
    val reviewCheck: Int
) : Parcelable

@Parcelize
data class StateWait(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount: Int,
    val mentoringMentos: Int,
    val mentoringMentorName: String,
    val mentoringMenteeName: String
) : Parcelable
