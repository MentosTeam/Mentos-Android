package com.mentos.mentosandroid.ui.otherprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.data.request.RequestReport
import com.mentos.mentosandroid.data.response.*
import kotlinx.coroutines.launch
import retrofit2.HttpException

class OneProfileViewModel : ViewModel() {

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

    private val _mentorPost2 = MutableLiveData<ArrayList<SearchMentor>>()
    val mentorPost2: LiveData<ArrayList<SearchMentor>>
        get() = _mentorPost2

    private val _mentorReview2 = MutableLiveData<ArrayList<Review>>()
    val mentorReview2: LiveData<ArrayList<Review>>
        get() = _mentorReview2

    private var _isSuccessReport = MutableLiveData<Boolean>()
    var isSuccessReport: LiveData<Boolean> = _isSuccessReport

    private val _isMyProfile = MutableLiveData<Boolean>()
    val isMyProfile: LiveData<Boolean> = _isMyProfile

    fun getMentorProfileData(memberId: Int) {
        viewModelScope.launch {
            try {
                val responseMentorProfile = ServiceBuilder.profileService.getMentorProfile(memberId)
                Log.d("멘토 정보", responseMentorProfile.message)
                mentorProfileDataItem = responseMentorProfile.result
                _mentorProfileData.value = mentorProfileDataItem
                getMentorData()

                if (SharedPreferenceController.getMemberId() == responseMentorProfile.result.basicInformation.memberId) {
                    _isMyProfile.postValue(true)
                } else {
                    _isMyProfile.postValue(false)
                }
            } catch (e: HttpException) {
                Log.d("멘토 정보", e.message().toString())
                Log.d("멘토 정보", e.code().toString())
            }
        }
    }

    private fun getMentorData() {
        if (mentorProfileDataItem.postArr.size >= 3) {
            _mentorPost2.value = arrayListOf(
                mentorProfileDataItem.postArr[0],
                mentorProfileDataItem.postArr[1]
            )
        } else {
            _mentorPost2.value = mentorProfileDataItem.postArr
        }
        if (mentorProfileDataItem.reviews.size >= 3) {
            _mentorReview2.value = arrayListOf(
                mentorProfileDataItem.reviews[0],
                mentorProfileDataItem.reviews[1]
            )
        } else {
            _mentorReview2.value = mentorProfileDataItem.reviews
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

        mentorProfileDataItem.numOfMentos.forEach { it ->
            for (i in 1..it.mentoringMentos)
                mentorMentosItems.add(it.majorCategoryId)
        }
        _mentorMentosList.value = mentorMentosItems
    }

    fun getMenteeProfileData(memberId: Int) {
        viewModelScope.launch {
            try {
                val responseMenteeProfile = ServiceBuilder.profileService.getMenteeProfile(memberId)
                Log.d("멘토 정보", responseMenteeProfile.message)
                menteeProfileDataItem = responseMenteeProfile.result
                _menteeProfileData.value = menteeProfileDataItem
                getMenteeData()

                if (SharedPreferenceController.getMemberId() == responseMenteeProfile.result.basicInformation.memberId) {
                    _isMyProfile.postValue(true)
                } else {
                    _isMyProfile.postValue(false)
                }
            } catch (e: HttpException) {
                Log.d("멘토 정보", e.message().toString())
                Log.d("멘토 정보", e.code().toString())
            }
        }
    }

    private fun getMenteeData() {
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

    fun postReport(flag: Int, number: Int, text: String) {
        viewModelScope.launch {
            try {
                val responseReport = ServiceBuilder.reportService.postReport(
                    RequestReport(flag, number, text)
                )
                Log.d("멘티 멘토 신고", responseReport.message)
                _isSuccessReport.value = responseReport.code == 1000
            } catch (e: HttpException) {
                Log.d("멘티 멘토 신고", e.message().toString())
                Log.d("멘티 멘토 신고", e.code().toString())
                _isSuccessReport.value = false
            }
        }
    }
}