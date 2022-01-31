package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReviewViewModel() : ViewModel() {
    private val _reviewData = MutableLiveData<ArrayList<String>>()
    val reviewData: LiveData<ArrayList<String>>
        get() = _reviewData
    private var reviewItems: ArrayList<String> =
        arrayListOf("후기 리스트 입니다1", "후기 리스트 입니다2", "후기 리스트 입니다3")

    init {

        _reviewData.value = reviewItems
    }
}
