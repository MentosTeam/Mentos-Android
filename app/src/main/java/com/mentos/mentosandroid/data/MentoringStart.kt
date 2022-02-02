package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MentoringStart(
    var majorCategoryId: Int?,
    var mentoId: Int?,
    var mentoringCount: Int?,
    var mentos: Int?
): Parcelable