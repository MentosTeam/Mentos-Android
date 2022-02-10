package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.ResponseCreateProfile
import com.mentos.mentosandroid.data.response.ResponseMyProfile
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProfileService {

    @GET("/profile/mypage")
    suspend fun getMyProfile(): ResponseMyProfile

    @Multipart
    @POST("/profile/setProfile")
    suspend fun postCreateProfile(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageFile: MultipartBody.Part?
    ): ResponseCreateProfile
}