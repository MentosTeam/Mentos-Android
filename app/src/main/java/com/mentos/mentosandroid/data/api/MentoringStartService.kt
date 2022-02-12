package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestMentoringStart
import com.mentos.mentosandroid.data.response.ResponseMentorNicName
import com.mentos.mentosandroid.data.response.ResponseMentoringAccept
import com.mentos.mentosandroid.data.response.ResponseMentoringStart
import retrofit2.http.*

interface MentoringStartService {

    @POST("/mentoring/registration")
    suspend fun postMentoringStart(
        @Body body: RequestMentoringStart
    ): ResponseMentoringStart

    @GET("/mentoring/registration/nickname")
    suspend fun getMentorNickName(
        @Query("mentoId") mentoId: Int
    ): ResponseMentorNicName

    @PATCH("/mentoring/acceptance")
    suspend fun patchMentoringAccept(
        @Query("mentoringId") mentoringId: Int,
        @Query("accept") accept: Boolean
    ): ResponseMentoringAccept
}