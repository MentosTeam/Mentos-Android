package com.mentos.mentosandroid.ui.notification

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
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
                if(responseNotice.isSuccess){
                    notiItems = responseNotice.result
                    _notiList.value = notiItems
                }else{
                    notiItems = arrayListOf(Notice("공지입니다", "2022-02-19T12:30:21.000+00:00", 1))
                    _notiList.value = notiItems
                }
            } catch (e: HttpException) {
                Log.d("공지사항", e.message().toString())
                Log.d("공지사항", e.code().toString())
                notiItems = arrayListOf(Notice("공지입니다", "2022-02-19T12:30:21.000+00:00", 1))
                _notiList.value = notiItems
            }
        }
    }

    fun getPush() {
        viewModelScope.launch {
            try {
               notiItems = arrayListOf(Notice("푸시 알림입니다", "2022-02-19T12:30:21.000+00:00", 1))
                _notiList.value = notiItems
            } catch (e: HttpException) {

            }
        }
    }

}
