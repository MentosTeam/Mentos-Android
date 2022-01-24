package com.mentos.mentosandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.MenteeCategory
import com.mentos.mentosandroid.data.Mentor
import com.mentos.mentosandroid.data.MentorCategory
import com.mentos.mentosandroid.data.Profile

class HomeViewModel() : ViewModel() {
    //other profile list
    private val _profileList = MutableLiveData<ArrayList<Profile>>()
    val profileList: LiveData<ArrayList<Profile>>
        get() = _profileList

    private var profileItems = ArrayList<Profile>()

    //menteeCategory list
    private val _menteeCategoryList = MutableLiveData<ArrayList<MenteeCategory>>()
    val menteeCategoryList: LiveData<ArrayList<MenteeCategory>>
        get() = _menteeCategoryList

    private var menteeCategoryItems = ArrayList<MenteeCategory>()

    //mentorCategory list
    private val _mentorCategoryList = MutableLiveData<ArrayList<MentorCategory>>()
    val mentorCategoryList: LiveData<ArrayList<MentorCategory>>
        get() = _mentorCategoryList

    private var mentorCategoryItems = ArrayList<MentorCategory>()


    init {
        initOtherProfileRV()

        initMenteeCategoryRV()

        initMentorCategoryRV()
    }

    private fun initMentorCategoryRV() {
        //mentorCategory
        mentorCategoryItems = arrayListOf(
            MentorCategory(
                "", "경제 / 경영", arrayListOf(
                    Mentor("", "[중어중문과 1학년 기초교양] 초급중국어 가나다 교수님 수업 수강했습니다(A+)"),
                    Mentor("", "[중어중문학과 2학년 전공] 중국문화의 이해 라마바 교수님 시험 및 기말 과제 수행에 도움 드릴 수 있습니다")
                )
            ), MentorCategory(
                "", "인문", arrayListOf(
                    Mentor("", "[중어중문과 1학년 기초교양] 초급중국어 가나다 교수님 수업 수강했습니다(A+)"),
                    Mentor("", "[중어중문학과 2학년 전공] 중국문화의 이해 라마바 교수님 시험 및 기말 과제 수행에 도움 드릴 수 있습니다")
                )
            )
        )
        _mentorCategoryList.value = mentorCategoryItems
    }

    private fun initMenteeCategoryRV() {
        //menteeCategory
        menteeCategoryItems = arrayListOf(
            MenteeCategory(
                "", "경제 / 경영", arrayListOf(
                    Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
                    Profile("", "홍길동 / 경제학과, 미디어커뮤니케이션학과 / 17학번", "#경제/경영, #인문")
                )
            ),
            MenteeCategory(
                "", "인문", arrayListOf(
                    Profile("", "홍길동 / 경영학과 / 19학번", "#인문, #어문"),
                    Profile("", "홍길동 / 경제학과, 미디어커뮤니케이션학과 / 17학번", "#경제/경영, #인문")
                )
            )
        )
        _menteeCategoryList.value = menteeCategoryItems
    }

    private fun initOtherProfileRV() {
        //other profile
        profileItems = arrayListOf(
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경제학과, 미디어커뮤니케이션학과 / 17학번", "#경제/경영, #인문")
        )
        _profileList.value = profileItems
    }

}