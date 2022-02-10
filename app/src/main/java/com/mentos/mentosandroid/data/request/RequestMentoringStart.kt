package com.mentos.mentosandroid.data.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RequestMentoringStart(
    var mentoId: Int?,
    var mentoringCount: Int?,
    var majorCategoryId: Int?,
    var mentos: Int?
): Parcelable