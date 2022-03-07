package com.mentos.mentosandroid.ui.mentoringstart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestMentoringStart
import com.mentos.mentosandroid.data.response.NickNameResult
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class MentoringStartViewModel : ViewModel() {

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    private val _nickName = MutableLiveData<NickNameResult>()
    val nickName: LiveData<NickNameResult> = _nickName

    private val _isSuccessAccept = MutableLiveData<Boolean>()
    val isSuccessAccept: LiveData<Boolean> = _isSuccessAccept

    fun postMentoringStart(mentoringStart: RequestMentoringStart) {
        viewModelScope.launch {
            try {
                val responseMentoringStart =
                    ServiceBuilder.mentoringStartService.postMentoringStart(mentoringStart)
                _isSuccess.value = responseMentoringStart.code == 1000
            } catch (e: HttpException) {
                Timber.d(e.message())
                _isSuccess.value = false
            }
        }
    }

    fun getNickName(mentorId: Int) {
        viewModelScope.launch {
            try {
                val responseMentorNicName =
                    ServiceBuilder.mentoringStartService.getMentorNickName(mentorId)
                if (responseMentorNicName.code == 1000) {
                    _nickName.value = responseMentorNicName.result
                }
            } catch (e: HttpException) {
                Timber.d(e.message())
            }
        }
    }

    fun patchMentoringAccept(mentoringId: Int, accept: Boolean) {
        viewModelScope.launch {
            try {
                val responseMentoringAccept =
                    ServiceBuilder.mentoringStartService.patchMentoringAccept(mentoringId, accept)
                _isSuccessAccept.value = responseMentoringAccept.isSuccess
            } catch (e: HttpException) {
                Timber.d(e.message())
                _isSuccessAccept.value = false
            }
        }
    }
}