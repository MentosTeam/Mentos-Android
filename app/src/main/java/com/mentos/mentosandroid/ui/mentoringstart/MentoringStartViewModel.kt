package com.mentos.mentosandroid.ui.mentoringstart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestMentoringStart
import com.mentos.mentosandroid.data.response.NickNameResult
import com.mentos.mentosandroid.data.response.ResponseMentorNicName
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MentoringStartViewModel : ViewModel() {

    fun postMentoringStart(mentoringStart: RequestMentoringStart): Boolean {
        var isSuccess = false
        viewModelScope.launch {
            try {
                val responseMentoringStart =
                    ServiceBuilder.mentoringStartService.postMentoringStart(mentoringStart)
                Log.d("멘토링 요청", mentoringStart.toString())
                Log.d("멘토링 요청", responseMentoringStart.message)
                if (responseMentoringStart.code == 1000) {
                    isSuccess = true
                } else {
                    Log.d("멘토링 요청", responseMentoringStart.code.toString())
                    Log.d("멘토링 요청", responseMentoringStart.message)
                    isSuccess = false
                }
            } catch (e: HttpException) {
                Log.d("멘토링 요청", e.code().toString())
                Log.d("멘토링 요청", e.message())
                isSuccess = false
            }
        }
        return isSuccess
    }

    fun getNickName(mentorId: Int): NickNameResult? {
        var nickNameResult: NickNameResult? = null
        viewModelScope.launch {
            try {
                val responseMentorNicName =
                    ServiceBuilder.mentoringStartService.getMentorNickName(mentorId)
                Log.d("닉네임", responseMentorNicName.message)
                if (responseMentorNicName.code == 1000) {
                    nickNameResult = responseMentorNicName.result
                } else {
                    Log.d("닉네임", responseMentorNicName.code.toString())
                    Log.d("닉네임", responseMentorNicName.message)
                }
            } catch (e: HttpException) {
                Log.d("닉네임", e.code().toString())
                Log.d("닉네임", e.message())
            }
        }
        return nickNameResult
    }
}