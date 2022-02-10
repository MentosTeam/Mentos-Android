package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestMentoringStart
import com.mentos.mentosandroid.data.response.ResponseMentorNicName
import com.mentos.mentosandroid.data.response.ResponseMentoringStart
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MentoringStartService {

    @POST("/mentoring/registration")
    suspend fun postMentoringStart(
        @Body body: RequestMentoringStart
    ): ResponseMentoringStart

    @GET("/mentoring/registration/nickname")
    suspend fun getMentorNickName(
        @Query("mentoId") mentoId: Int
    ): ResponseMentorNicName
}