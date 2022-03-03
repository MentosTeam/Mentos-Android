package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestChangeMentos
import com.mentos.mentosandroid.data.request.RequestChangePW
import com.mentos.mentosandroid.data.request.RequestWithdrawal
import com.mentos.mentosandroid.data.response.BaseResponse
import com.mentos.mentosandroid.data.response.ResponseSetting
import com.mentos.mentosandroid.data.response.ResponseWithdrawal
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface SettingService {
    @GET("/settings/mento")
    suspend fun getSettingMentor(): ResponseSetting

    @GET("/settings/mentee")
    suspend fun getSettingMentee(): ResponseSetting

    @POST("/settings/profile/school")
    suspend fun postMajor(
        @Query("major") major: String
    ): BaseResponse

    @POST("/settings/profile")
    suspend fun postNickName(
        @Query("nickName") nickName: String
    ): BaseResponse

    @POST("/settings/profile/intro")
    suspend fun postMentosIntro(
        @Body body: RequestChangeMentos
    ): BaseResponse

    @Multipart
    @POST("/settings/profile/image")
    suspend fun postImage(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageFile: MultipartBody.Part?
    ): BaseResponse

    @POST("/members/pwChange")
    suspend fun postPW(
        @Body body: RequestChangePW
    ): BaseResponse

    @PATCH("/setting/member-leave")
    suspend fun postWithdrawal(
        @Body body: RequestWithdrawal
    ): ResponseWithdrawal

    @POST("/settings/profile/gender")
    suspend fun postSetOpenSex(): BaseResponse

    @POST("/settings/profile/notification")
    suspend fun postSetSendNotification(): BaseResponse
}