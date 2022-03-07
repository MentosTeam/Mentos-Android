package com.mentos.mentosandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestNewDeviceFcmToken
import com.mentos.mentosandroid.data.response.HomeMenteeResult
import com.mentos.mentosandroid.data.response.HomeMentorResult
import com.mentos.mentosandroid.data.response.MenteeCategory
import com.mentos.mentosandroid.data.response.MentorCategory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

const val ONLY_MENTOR = 1
const val ONLY_MENTEE = 2
const val BOTH = 3

class HomeViewModel() : ViewModel() {
    private val _menteeHomeData = MutableLiveData<HomeMenteeResult>()
    val menteeHomeData: LiveData<HomeMenteeResult>
        get() = _menteeHomeData
    private lateinit var menteeHomeDataItem: HomeMenteeResult

    private val _mentorHomeData = MutableLiveData<HomeMentorResult>()
    val mentorHomeData: LiveData<HomeMentorResult>
        get() = _mentorHomeData
    private lateinit var mentorHomeDataItem: HomeMentorResult


    fun getMentorData() {
        viewModelScope.launch() {
            try {
                val responseHomeMentor = ServiceBuilder.homeService.getHomeMentor()
                mentorHomeDataItem = responseHomeMentor.result
                _mentorHomeData.value = mentorHomeDataItem
            } catch (e: HttpException) {
                mentorHomeDataItem = HomeMentorResult(
                    arrayListOf(MenteeCategory(arrayListOf(), 1)),
                    10,
                    arrayListOf()
                )
                _mentorHomeData.value = mentorHomeDataItem
                Timber.d( e.message().toString())
            }
        }
    }

    fun getMenteeData() {
        viewModelScope.launch() {
            try {
                val responseHomeMentee = ServiceBuilder.homeService.getHomeMentee()
                menteeHomeDataItem = responseHomeMentee.result
                _menteeHomeData.value = menteeHomeDataItem
            } catch (e: HttpException) {
                menteeHomeDataItem = HomeMenteeResult(
                    arrayListOf(MentorCategory(1, arrayListOf())),
                    3,
                    arrayListOf()
                )
                _mentorHomeData.value = mentorHomeDataItem
                Timber.d(e.message().toString())
            }
        }
    }

    fun postDeviceFcmToken(currToken: String, newToken: String) {
        viewModelScope.launch {
            try {
                val responseFcmToken = ServiceBuilder.authService.postDeviceFcmToken(
                    RequestNewDeviceFcmToken(
                        currToken,
                        newToken
                    )
                )
                Timber.d(responseFcmToken.message)
            } catch (e: HttpException) {
                Timber.d(e.message().toString())
            }
        }
    }
}