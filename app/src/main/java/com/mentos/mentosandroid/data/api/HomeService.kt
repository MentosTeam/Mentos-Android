package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.ResponseHomeMentee
import com.mentos.mentosandroid.data.response.ResponseHomeMentor
import retrofit2.http.GET

interface HomeService {

    @GET("/mentor/main")
    suspend fun getHomeMentor(): ResponseHomeMentor

    @GET("/mentee/main")
    suspend fun getHomeMentee(): ResponseHomeMentee
}