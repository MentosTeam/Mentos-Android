package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.response.Review

class ReviewViewModel() : ViewModel() {
    private val _reviewData = MutableLiveData<ArrayList<Review>>()
    val reviewData: LiveData<ArrayList<Review>>
        get() = _reviewData
    private var reviewItems: ArrayList<Review> = arrayListOf()

    fun setReviewData(reviewList: ArrayList<Review>){
        _reviewData.value = reviewList
    }
}
