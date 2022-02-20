package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.request.RequestReport
import com.mentos.mentosandroid.data.response.ResponseWithdrawal
import retrofit2.http.Body
import retrofit2.http.POST

interface ReportService {

    @POST("/complain")
    suspend fun postReport(
        @Body body: RequestReport
    ): ResponseWithdrawal
}