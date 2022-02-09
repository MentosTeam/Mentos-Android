package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.ResponseMyProfile
import retrofit2.http.GET

interface ProfileService {

    @GET("/profile/mypage")
    suspend fun getMyProfile(): ResponseMyProfile
}