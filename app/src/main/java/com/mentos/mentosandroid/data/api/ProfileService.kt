package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ProfileService {

    @GET("/profile/mypage")
    suspend fun getMyProfile(): ResponseMyProfile

    @GET("/profile/myposts")
    suspend fun getMyPostList(): ResponseMyPostList

    @GET("/profile/mentor")
    suspend fun getMentorProfile(
        @Query("memberId") memberId: Int
    ): ResponseMentorProfile

    @GET("/profile/mentee")
    suspend fun getMenteeProfile(
        @Query("memberId") memberId: Int
    ): ResponseMenteeProfile

    @Multipart
    @POST("/profile/setProfile")
    suspend fun postCreateProfile(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageFile: MultipartBody.Part?
    ): ResponseCreateProfile
}