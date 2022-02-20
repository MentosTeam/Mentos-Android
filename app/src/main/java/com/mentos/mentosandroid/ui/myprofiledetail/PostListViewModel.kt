package com.mentos.mentosandroid.ui.myprofiledetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestReport
import com.mentos.mentosandroid.data.response.SearchMentor
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PostListViewModel : ViewModel() {

    private val _myPostList = MutableLiveData<ArrayList<SearchMentor>>()
    val myPostList: LiveData<ArrayList<SearchMentor>> = _myPostList

    private var _isSuccessReport = MutableLiveData<Boolean>()
    var isSuccessReport: LiveData<Boolean> = _isSuccessReport

    fun getMyPostList() {
        viewModelScope.launch {
            try {
                val responseMyPostList = ServiceBuilder.profileService.getMyPostList()
                Log.d("내가 쓴 글", responseMyPostList.message)
                _myPostList.value = responseMyPostList.result
            }catch (e: HttpException){
                Log.d("내가 쓴 글", e.code().toString())
                Log.d("내가 쓴 글", e.message().toString())
            }
        }
    }

    fun postReport(flag: Int, number: Int, text: String) {
        viewModelScope.launch {
            try {
                val responseReport = ServiceBuilder.reportService.postReport(
                    RequestReport(flag, number, text)
                )
                Log.d("글 신고", responseReport.message)
                _isSuccessReport.value = responseReport.code == 1000
            } catch (e: HttpException) {
                Log.d("글 신고", e.message().toString())
                Log.d("글 신고", e.code().toString())
                _isSuccessReport.value = false
            }
        }
    }

    fun initSuccessReport() {
        _isSuccessReport = MutableLiveData<Boolean>()
        isSuccessReport = _isSuccessReport
    }
}