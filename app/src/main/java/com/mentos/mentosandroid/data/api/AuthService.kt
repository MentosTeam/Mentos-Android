package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestFindPw
import com.mentos.mentosandroid.data.request.RequestSchoolCheck
import com.mentos.mentosandroid.data.request.RequestSignIn
import com.mentos.mentosandroid.data.request.RequestSignUp
import com.mentos.mentosandroid.data.response.BaseResponse
import com.mentos.mentosandroid.data.response.ResponseSchoolCheck
import com.mentos.mentosandroid.data.response.ResponseSignIn
import com.mentos.mentosandroid.data.response.ResponseSignUp
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("/members/log-in")
    suspend fun postSignIn(
        @Body body: RequestSignIn
    ): ResponseSignIn

    @POST("/members/sign-up")
    suspend fun postSignUp(
        @Body body: RequestSignUp
    ): ResponseSignUp

    @GET("/members/nickNameChk")
    suspend fun getNickNameCheck(
        @Query("nickName") nickName: String
    ): BaseResponse

    @POST("/schoolCertification")
    suspend fun postSchoolCheck(
        @Body body: RequestSchoolCheck
    ) : ResponseSchoolCheck

    @POST("/members/pwInquiry")
    suspend fun postFindPw(
        @Body body: RequestFindPw
    ) : BaseResponse
}