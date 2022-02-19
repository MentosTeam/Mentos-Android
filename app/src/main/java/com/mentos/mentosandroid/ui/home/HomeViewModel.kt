package com.mentos.mentosandroid.ui.home

import android.util.Log
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
                Log.d("홈 멘토", responseHomeMentor.message)
                mentorHomeDataItem = responseHomeMentor.result
                _mentorHomeData.value = mentorHomeDataItem
            } catch (e: HttpException) {
                mentorHomeDataItem = HomeMentorResult(
                    arrayListOf(MenteeCategory(arrayListOf(), 1)),
                    10,
                    arrayListOf()
                )
                _mentorHomeData.value = mentorHomeDataItem
                Log.d("홈 멘토", e.message().toString())
                Log.d("홈 멘토", e.code().toString())
            }
        }
    }

    fun getMenteeData() {
        viewModelScope.launch() {
            try {
                val responseHomeMentee = ServiceBuilder.homeService.getHomeMentee()
                Log.d("홈 멘티", responseHomeMentee.message)
                menteeHomeDataItem = responseHomeMentee.result
                _menteeHomeData.value = menteeHomeDataItem
            } catch (e: HttpException) {
                menteeHomeDataItem = HomeMenteeResult(
                    arrayListOf(MentorCategory(1, arrayListOf())),
                    3,
                    arrayListOf()
                )
                _mentorHomeData.value = mentorHomeDataItem
                Log.d("홈 멘티", e.message().toString())
                Log.d("홈 멘티", e.code().toString())
            }
        }
    }

    fun postDeviceFcmToken(currToken: String, newToken: String){
        viewModelScope.launch {
            try {
                val responseFcmToken = ServiceBuilder.authService.postDeviceFcmToken(
                    RequestNewDeviceFcmToken(
                        currToken,
                        newToken
                    )
                )
                Log.d("홈 fcm token", responseFcmToken.message)
            }catch (e: HttpException){
                Log.d("홈 fcm token", e.message().toString())
                Log.d("홈 fcm token", e.code().toString())
            }
        }
    }
}