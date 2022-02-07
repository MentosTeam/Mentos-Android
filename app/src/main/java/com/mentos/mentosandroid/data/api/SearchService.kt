package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.ResponseSearchMentee
import com.mentos.mentosandroid.data.response.ResponseSearchMentor
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("/mentee/search")
    suspend fun getSearchMentor(
        @Query("majorFlag") majorFlag: List<Int>,
        @Query("searchText") searchText: String?
    ): ResponseSearchMentor


    @GET("/mentor/search")
    suspend fun getSearchMentee(
        @Query("majorFlag") majorFlag: List<Int>,
        @Query("searchText") searchText: String?
    ): ResponseSearchMentee
}