package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestReview
import com.mentos.mentosandroid.data.request.RequestStateRecord
import com.mentos.mentosandroid.data.response.*
import retrofit2.http.*

interface StateService {

    @GET("/members/mentoring/mentor")
    suspend fun getMentorState(): ResponseStateMentor

    @GET("/members/mentoring/mentee")
    suspend fun getMenteeState(): ResponseStateMentee

    @POST("/mentoring/review/{mentoringId}")
    suspend fun postReview(
        @Path("mentoringId") mentoringId: Int,
        @Body body: RequestReview
    ): ResponseStateReview

    @POST("/report")
    suspend fun postRecord(
        @Body body: RequestStateRecord
    ): ResponseRecordPost

    @GET("/report")
    suspend fun getRecord(
        @Query("mentoringId") mentoringId: Int
    ): ResponseRecord
}