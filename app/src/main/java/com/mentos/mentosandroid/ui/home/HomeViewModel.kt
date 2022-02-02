package com.mentos.mentosandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.*

const val ONLY_MENTOR = 1
const val ONLY_MENTEE = 2
const val BOTH = 3

class HomeViewModel() : ViewModel() {
    private val _menteeHomeData = MutableLiveData<MenteeHome>()
    val menteeHomeData: LiveData<MenteeHome>
        get() = _menteeHomeData
    private lateinit var menteeHomeDataItem: MenteeHome

    private val _mentorHomeData = MutableLiveData<MentorHome>()
    val mentorHomeData: LiveData<MentorHome>
        get() = _mentorHomeData
    private lateinit var mentorHomeDataItem: MentorHome

    init {
//        //프로필 종류 검사 후 데이터 적용
//        chkProfile()
        getMentorData()
        getMenteeData()
    }

    private fun chkProfile() {
        val profileState = 3
        when (profileState) {
            ONLY_MENTOR -> {
                getMentorData()
            }
            ONLY_MENTEE -> {
                getMenteeData()
            }
            BOTH -> {
                getMentorData()
                getMenteeData()
            }
        }
    }

    private fun getMenteeData() {
        menteeHomeDataItem =
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

        _menteeHomeData.value = menteeHomeDataItem
    }

    private fun getMentorData() {
        mentorHomeDataItem =
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

        _mentorHomeData.value = mentorHomeDataItem
    }
}