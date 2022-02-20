package com.mentos.mentosandroid.ui.chat

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestReport
import kotlinx.coroutines.launch
import retrofit2.HttpException

class ChatViewModel : ViewModel() {

    private var _isSuccessReport = MutableLiveData<Boolean>()
    var isSuccessReport: LiveData<Boolean> = _isSuccessReport

    fun postReport(flag: Int, number: Int, text: String) {
        viewModelScope.launch {
            try {
                val responseReport = ServiceBuilder.reportService.postReport(
                    RequestReport(flag, number, text)
                )
                Log.d("채팅 신고", responseReport.message)
                _isSuccessReport.value = responseReport.code == 1000
            } catch (e: HttpException) {
                Log.d("채팅 신고", e.message().toString())
                Log.d("채팅 신고", e.code().toString())
                _isSuccessReport.value = false
            }
        }
    }
}