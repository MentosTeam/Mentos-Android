package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestChangeMentos
import com.mentos.mentosandroid.data.request.RequestChangeProfileImg
import com.mentos.mentosandroid.data.request.RequestChangePW
import com.mentos.mentosandroid.data.response.BaseResponse
import com.mentos.mentosandroid.data.response.ResponseSetting
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

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

    @POST("/settings/profile/image")
    suspend fun postImage(
        @Body body: RequestChangeProfileImg
    ): BaseResponse

    @POST("/members/pwChange")
    suspend fun postPW(
        @Body body: RequestChangePW
    ): BaseResponse
}