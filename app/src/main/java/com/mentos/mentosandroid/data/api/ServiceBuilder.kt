package com.mentos.mentosandroid.data.api

import com.mentos.mentosandroid.util.SharedPreferenceController
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private const val BASE_URL = "http://13.124.36.33:8080"

    private val interceptor = Interceptor { chain ->
        with(chain) {
            val newRequest = request().newBuilder()
                .addHeader("X-ACCESS-TOKEN", SharedPreferenceController.getJwtToken()!!)
                .build()
            proceed(newRequest)
        }
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val searchService: SearchService = retrofit.create(SearchService::class.java)
}