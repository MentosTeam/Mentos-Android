package com.mentos.mentosandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.*

class HomeViewModel() : ViewModel() {
    private val _menteeHomeData = MutableLiveData<MenteeHome>()
    val menteeHomeData: LiveData<MenteeHome>
        get() = _menteeHomeData
    private var menteeHomeDataItem: MenteeHome =
        MenteeHome(
            5,
            arrayListOf(
                MentorCategory(
                    3,
                    arrayListOf(
                        MentorPost(1, "현진", "소프트웨어", "", 10, 3, "C언어 알려드려요", "C언어 본문1", null),
                        MentorPost(2, "가은", "소프트웨어", "", 11, 3, "JAVA 알려드려요", "JAVA 본문1", null)
                    )
                ),
                MentorCategory(
                    4,
                    arrayListOf(
                        MentorPost(3, "현진", "소프트웨어", "", 12, 4, "C언어 알려드려요", "C언어 본문1", null),
                        MentorPost(4, "가은", "소프트웨어", "", 13, 4, "JAVA 알려드려요", "JAVA 본문1", null)
                    )
                )
            ),
            arrayListOf(
                OtherMentor(5, "준원", "컴퓨터공학", "18학번", "", 1, 2),
                OtherMentor(6, "준원", "컴퓨터공학", "18학번", "", 5, 6)
            )
        )

    private val _mentorHomeData = MutableLiveData<MentorHome>()
    val mentorHomeData: LiveData<MentorHome>
        get() = _mentorHomeData
    private var mentorHomeDataItem: MentorHome =
        MentorHome(
            7,
            arrayListOf(
                MenteeCategory(
                    3,
                    arrayListOf(
                        Mentee(1, "현진", "소프트웨어", "18학번", "", 3, 4),
                        Mentee(2, "가은", "소프트웨어", "18학번", "", 3, 5)
                    )
                ),
                MenteeCategory(
                    4,
                    arrayListOf(
                        Mentee(1, "현진", "소프트웨어", "18학번", "", 4, 5),
                        Mentee(2, "가은", "소프트웨어", "18학번", "", 4, 6)
                    )
                )
            ),
            arrayListOf(
                Mentee(5, "준원", "컴퓨터공학", "18학번", "", 1, 2),
                Mentee(6, "준원", "컴퓨터공학", "18학번", "", 5, 6)
            )
        )

    init {
        _menteeHomeData.value = menteeHomeDataItem
        _mentorHomeData.value = mentorHomeDataItem
    }


    private fun initOtherProfileRV() {

    }

}