package com.mentos.mentosandroid.data.response

data class ResponseStateReview(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: ReviewResult
)

data class ReviewResult(
    val reviewId: Int,
    val reviewScore: Double
)