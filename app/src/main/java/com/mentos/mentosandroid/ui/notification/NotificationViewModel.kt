package com.mentos.mentosandroid.ui.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.data.response.Notice
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NotificationViewModel : ViewModel() {

    private val _notiList = MutableLiveData<ArrayList<Notice>>()
    val notiList: LiveData<ArrayList<Notice>>
        get() = _notiList
    private lateinit var notiItems: ArrayList<Notice>

    fun getNotification() {
        viewModelScope.launch {
            try {
                val responseNotice = ServiceBuilder.reportService.getNotice()
                Log.d("공지사항", responseNotice.message)
                if (responseNotice.isSuccess) {
                    notiItems = responseNotice.result
                    _notiList.value = notiItems
                } else {
                    notiItems = arrayListOf(Notice("공지입니다", "2022-02-19T12:30:21.000+00:00", 1))
                    _notiList.value = notiItems
                }
            } catch (e: HttpException) {
                Log.d("공지사항", e.message().toString())
                notiItems = arrayListOf(Notice("공지입니다", "2022-02-19T12:30:21.000+00:00", 1))
                _notiList.value = notiItems
            }
        }
    }

    fun getPush() {
        viewModelScope.launch {
            try {
                val statusFlag = when (SharedPreferenceController.getNowState()) {
                    0 -> 1
                    1 -> 2
                    else -> 2
                }
                val responsePush = ServiceBuilder.reportService.getPushList(statusFlag)
                Log.d("푸쉬알림", responsePush.message)
                if (responsePush.isSuccess) {
                     notiItems = responsePush.result
                    _notiList.value = notiItems
                } else {
                    notiItems = arrayListOf(Notice("푸시 알림입니다", "2022-02-19T12:30:21.000+00:00", 1))
                    _notiList.value = notiItems
                }
            } catch (e: HttpException) {
                notiItems = arrayListOf(Notice("푸시 알림입니다", "2022-02-19T12:30:21.000+00:00", 1))
                _notiList.value = notiItems
            }
        }
    }

}
