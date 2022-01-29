package com.mentos.mentosandroid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel() : ViewModel() {

    private val _majorList = MutableLiveData<ArrayList<Int>>()
    val majorList: LiveData<ArrayList<Int>>
        get() = _majorList
    private var majorItems = ArrayList<Int>()

    private val _majorDetailList = MutableLiveData<ArrayList<MajorDetail>>()
    val majorDetailList: LiveData<ArrayList<MajorDetail>>
        get() = _majorDetailList
    private var majorDetailItems = ArrayList<MajorDetail>()

    private val _reviewList = MutableLiveData<ArrayList<String>>()
    val reviewList: LiveData<ArrayList<String>>
        get() = _reviewList
    private var reviewItems = ArrayList<String>()

    private val _mentosList = MutableLiveData<ArrayList<Int>>()
    val mentosList: LiveData<ArrayList<Int>>
        get() = _mentosList
    private var mentosItems = ArrayList<Int>()
    var strMentosCount = ""

    init {
        initRV()
    }

    private fun initRV() {
        //Major
        majorItems = arrayListOf(3, 4)
        _majorList.value = majorItems

        //MajorDetail
        majorDetailItems = arrayListOf(
            MajorDetail(1, "중국어 수업 가능합니다"),
            MajorDetail(8, " C언어 수업 가능합니다")
        )
        _majorDetailList.value = majorDetailItems

        //Review
        reviewItems = arrayListOf(
            "중어중문학과 학생입니다! 덕분에 000교수님 기초 중국어 A+맞았어요! 감사합니다! 기초 중국어 때문에 문제 있으셨던 분들! 꼭 이 분 찾아가세요!",
            "경영학 A+ 받았어요~~~~~~~~~~~~~~"
        )
        _reviewList.value = reviewItems

        //Mentos
        mentosItems = arrayListOf(1, 2, 3, 4, 5, 6, 0, 0, 0, 0)
        _mentosList.value = mentosItems

        var mentosCount = 0
        mentosItems.forEach {
            if (it != 0)
                mentosCount++
        }
        strMentosCount = "$mentosCount/10"
    }
}