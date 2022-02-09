package com.mentos.mentosandroid.data.request

import retrofit2.http.Multipart
import java.io.File

data class RequestChangeProfileImg(
    val role: Int,
    val imageUrl: String,
    val imageFile: String
)