package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StateEnd(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val mentoringCount1: Int,
    val mentoringCount2: Int,
    val mentos: Int,
    val mentoNickname: String,
    val mentiNickname: String,
    val review: Boolean
) : Parcelable
