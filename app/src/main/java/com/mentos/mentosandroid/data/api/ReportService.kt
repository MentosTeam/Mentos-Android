package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestReport
import com.mentos.mentosandroid.data.response.ResponseNotice
import com.mentos.mentosandroid.data.response.ResponseWithdrawal
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReportService {

    @POST("/complain")
    suspend fun postReport(
        @Body body: RequestReport
    ): ResponseWithdrawal

    @GET("/notice")
    suspend fun getNotice(): ResponseNotice

    @GET("/notification")
    suspend fun getPushList(
        @Query("statusFlag") statusFlag: Int
    ): ResponseNotice
}