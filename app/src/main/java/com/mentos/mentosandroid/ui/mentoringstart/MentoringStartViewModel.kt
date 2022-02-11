package com.mentos.mentosandroid.ui.mentoringstart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestMentoringStart
import com.mentos.mentosandroid.data.response.NickNameResult
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MentoringStartViewModel : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _nickName = MutableLiveData<NickNameResult>()
    val nickName: LiveData<NickNameResult> = _nickName

    fun postMentoringStart(mentoringStart: RequestMentoringStart) {
        viewModelScope.launch {
            try {
                val responseMentoringStart =
                    ServiceBuilder.mentoringStartService.postMentoringStart(mentoringStart)
                Log.d("멘토링 요청", mentoringStart.toString())
                Log.d("멘토링 요청", responseMentoringStart.message)
                if (responseMentoringStart.code == 1000) {
                    _isSuccess.value = true
                } else {
                    Log.d("멘토링 요청", responseMentoringStart.code.toString())
                    Log.d("멘토링 요청", responseMentoringStart.message)
                    _isSuccess.value = false
                }
            } catch (e: HttpException) {
                Log.d("멘토링 요청", e.code().toString())
                Log.d("멘토링 요청", e.message())
                _isSuccess.value = false
            }
        }
    }

    fun getNickName(mentorId: Int) {
        viewModelScope.launch {
            try {
                val responseMentorNicName =
                    ServiceBuilder.mentoringStartService.getMentorNickName(mentorId)
                Log.d("닉네임", responseMentorNicName.message)
                if (responseMentorNicName.code == 1000) {
                    _nickName.value = responseMentorNicName.result
                } else {
                    Log.d("닉네임", responseMentorNicName.code.toString())
                    Log.d("닉네임", responseMentorNicName.message)
                }
            } catch (e: HttpException) {
                Log.d("닉네임", e.code().toString())
                Log.d("닉네임", e.message())
            }
        }
    }
}