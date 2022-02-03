package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Review

class ReviewViewModel() : ViewModel() {
    private val _reviewData = MutableLiveData<ArrayList<Review>>()
    val reviewData: LiveData<ArrayList<Review>>
        get() = _reviewData
    private var reviewItems: ArrayList<Review> =
        arrayListOf(
            Review(
                1, 12, 4.5, "자바 리뷰1"
            ),
            Review(
                2, 14, 4.5, "파이썬 리뷰1"
            ),
            Review(
                3, 15, 4.5, "기계학습 리뷰1"
            )
        )

    init {

        _reviewData.value = reviewItems
    }
}
