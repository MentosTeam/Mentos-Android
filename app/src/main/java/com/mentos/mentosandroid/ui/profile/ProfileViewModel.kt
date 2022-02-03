package com.mentos.mentosandroid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.*

class ProfileViewModel() : ViewModel() {

    private val _menteeMajorList = MutableLiveData<ArrayList<Int>>()
    val menteeMajorList: LiveData<ArrayList<Int>>
        get() = _menteeMajorList
    private var menteeMajorItems = ArrayList<Int>()

    private val _mentorMajorList = MutableLiveData<ArrayList<Int>>()
    val mentorMajorList: LiveData<ArrayList<Int>>
        get() = _mentorMajorList
    private var mentorMajorItems = ArrayList<Int>()

    private val _mentorMentosList = MutableLiveData<ArrayList<Int>>()
    val mentorMentosList: LiveData<ArrayList<Int>>
        get() = _mentorMentosList
    private var mentorMentosItems = ArrayList<Int>()

    private val _menteeProfileData = MutableLiveData<MenteeProfile>()
    val menteeProfileData: LiveData<MenteeProfile>
        get() = _menteeProfileData
    private lateinit var menteeProfileDataItem: MenteeProfile

    private val _mentorProfileData = MutableLiveData<MentorProfile>()
    val mentorProfileData: LiveData<MentorProfile>
        get() = _mentorProfileData
    private lateinit var mentorProfileDataItem: MentorProfile

    private val _mentorPost2 = MutableLiveData<ArrayList<MyPost>>()
    val mentorPost2: LiveData<ArrayList<MyPost>>
        get() = _mentorPost2

    private val _mentorReview2 = MutableLiveData<ArrayList<Review>>()
    val mentorReview2: LiveData<ArrayList<Review>>
        get() = _mentorReview2


    init {
        getMentorData()
        getMenteeData()
    }

    private fun getMenteeData() {
        menteeProfileDataItem = MenteeProfile(
            "세종대학교",
            3,
            MenteeBasicInformation(
                5, "이현진", "와죠스키", 18, "F",
                "데이터사이언스", "", 10, 6, 0, "안녕하세요 코틀린 알려주세요~~"
            )
        )
        _menteeProfileData.value = menteeProfileDataItem


        //Major
        menteeMajorItems =
            if (menteeProfileDataItem.basicInformation.majorSecond == 0) {
                arrayListOf(menteeProfileDataItem.basicInformation.majorFirst)
            } else {
                arrayListOf(
                    menteeProfileDataItem.basicInformation.majorFirst,
                    menteeProfileDataItem.basicInformation.majorSecond
                )
            }
        _menteeMajorList.value = menteeMajorItems
    }

    private fun getMentorData() {
        mentorProfileDataItem = MentorProfile(
            "세종대학교",
            9,
            MentorBasicInformation(
                5, "이현진", "와죠스키", 18, "F",
                "데이터사이언스", "", 4.9, 10, 6, 7, "안녕하세요 자바 알려드립니다~!"
            ),
            arrayListOf(
                MyPost(
                    5, 6, 11, "자바 알려드려요", "자바 본문1", ""
                ),
                MyPost(
                    5, 7, 12, "파이썬 알려드려요", "파이썬 본문1", ""
                ),
                MyPost(
                    5, 7, 13, "기계학습 알려드려요", "기계학습 본문1", ""
                )
            ),
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
            ),
            arrayListOf(
                NumOfMento(
                    6, 12, 2
                ),
                NumOfMento(
                    7, 14, 3
                ),
                NumOfMento(
                    6, 15, 1
                )
            )
        )
        _mentorProfileData.value = mentorProfileDataItem

        //프로필에서는 2개씩만 보여줌
        if(mentorProfileDataItem.posts.size >= 3){
            _mentorPost2.value = arrayListOf(
                mentorProfileDataItem.posts[0],
                mentorProfileDataItem.posts[1]
            )
        }
        if(mentorProfileDataItem.reviews.size >= 3){
            _mentorReview2.value = arrayListOf(
                mentorProfileDataItem.reviews[0],
                mentorProfileDataItem.reviews[1]
            )
        }

        mentorMajorItems =
            if (mentorProfileDataItem.basicInformation.majorSecond == 0) {
                arrayListOf(mentorProfileDataItem.basicInformation.majorFirst)
            } else {
                arrayListOf(
                    mentorProfileDataItem.basicInformation.majorFirst,
                    mentorProfileDataItem.basicInformation.majorSecond
                )
            }
        _mentorMajorList.value = mentorMajorItems

        //진행한 멘토링-멘토스 리스트
        mentorProfileDataItem.numOfMentos.forEach { it ->
            mentorMentosItems.add(it.majorCategoryId)
        }

        if(mentorMentosItems.size < 10){
            for(i in mentorMentosItems.size until 10){
                mentorMentosItems.add(i, 0)
            }
        }else{
            //10개 이상이면 최근 10개만 보여줌 -> api 받아올 때 최신순인지 확인 필요
            while(mentorMentosItems.size > 10){
                mentorMentosItems.removeAt(0)
            }
        }

        _mentorMentosList.value = mentorMentosItems
    }

}