package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestSignIn
import com.mentos.mentosandroid.data.request.RequestSignUp
import com.mentos.mentosandroid.data.response.BaseResponse
import com.mentos.mentosandroid.data.response.ResponseSignIn
import com.mentos.mentosandroid.data.response.ResponseSignUp
import retrofit2.http.Body
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

    @POST("/members/nickNameChk")
    suspend fun getNickNameCheck(
        @Query("nickName") nickName: String
    ): BaseResponse
}