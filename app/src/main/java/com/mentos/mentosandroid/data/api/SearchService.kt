package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.data.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

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

    @GET("/category")
    suspend fun getSearchCategory(
        @Query("flag") flag: Int
    ): ResponseSearchCategory

    @Multipart
    @POST("/posts")
    suspend fun postSearchCreate(
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageFile: MultipartBody.Part?
    ): ResponseSearchCreate

    @DELETE("/posts/{postId}")
    suspend fun deleteMentorPost(
        @Path("postId") postId: Int
    ): BaseResponse

    @Multipart
    @PATCH("/posts/{postId}")
    suspend fun modifyMentorPost(
        @Path("postId") postId: Int,
        @PartMap map: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part imageFile: MultipartBody.Part?
    ): BaseResponse
}