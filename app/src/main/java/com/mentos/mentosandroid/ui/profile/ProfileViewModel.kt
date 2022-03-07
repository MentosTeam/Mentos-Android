package com.mentos.mentosandroid.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.response.*
import com.mentos.mentosandroid.data.api.ServiceBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class ProfileViewModel : ViewModel() {

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


    private val _myProfileData = MutableLiveData<MyProfileResult>()
    val myProfileData: LiveData<MyProfileResult>
        get() = _myProfileData
    private lateinit var myProfileDataItem: MyProfileResult

    private var _profileState = MutableLiveData<Int>()
    var profileState: LiveData<Int> = _profileState

    fun getMyProfileData() {
        _mentorMentosList.value = ArrayList()
        mentorMentosItems = ArrayList()
        viewModelScope.launch {
            try {
                val responseMyProfile = ServiceBuilder.profileService.getMyProfile()
                myProfileDataItem = responseMyProfile.result
                _myProfileData.value = myProfileDataItem

                when {
                    responseMyProfile.result.menteeProfile == null -> {
                        _profileState.value = 1
                        getMentorData()
                    }
                    responseMyProfile.result.mentorProfile == null -> {
                        _profileState.value = 2
                        getMenteeData()
                    }
                    else -> {
                        _profileState.value = 3
                        getMentorData()
                        getMenteeData()
                    }
                }
            } catch (e: HttpException) {
                Timber.d(e.message().toString())
            }
        }
    }

    private fun getMenteeData() {
        menteeProfileDataItem = myProfileDataItem.menteeProfile!!
        _menteeProfileData.value = menteeProfileDataItem

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
        mentorProfileDataItem = myProfileDataItem.mentorProfile!!
        _mentorProfileData.value = mentorProfileDataItem

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

        mentorProfileDataItem.numOfMentos.forEach {
            for (i in 1..it.mentoringMentos)
                mentorMentosItems.add(it.majorCategoryId)
        }
        _mentorMentosList.value = mentorMentosItems
    }

}