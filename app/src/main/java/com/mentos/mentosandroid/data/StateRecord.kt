package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StateRecord(
    val mentoringId: Int,
    val majorCategoryId: Int,
    val count: Int,
    val date: String,
    val recordContent: String,
) : Parcelable
