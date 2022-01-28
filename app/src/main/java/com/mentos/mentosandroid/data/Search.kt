package com.mentos.mentosandroid.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Search(
    val postTitle: String,
    val majorCategoryId: Int,
    val mentoId: Int,
    val imageUrl: String?,
    val mentoNickName: String,
    val postContents: String,
    val postId: Int
) : Parcelable
