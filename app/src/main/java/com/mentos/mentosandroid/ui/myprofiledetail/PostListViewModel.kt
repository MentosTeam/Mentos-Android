package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestReport
import com.mentos.mentosandroid.data.response.SearchMentor
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class PostListViewModel : ViewModel() {

    private val _myPostList = MutableLiveData<ArrayList<SearchMentor>>()
    val myPostList: LiveData<ArrayList<SearchMentor>> = _myPostList

    private var _isSuccessReport = MutableLiveData<Boolean>()
    var isSuccessReport: LiveData<Boolean> = _isSuccessReport

    private var _setLoading = MutableLiveData<Boolean>()
    var setLoading: LiveData<Boolean> = _setLoading

    fun getMyPostList() {
        viewModelScope.launch {
            try {
                val responseMyPostList = ServiceBuilder.profileService.getMyPostList()
                Timber.d(responseMyPostList.message)
                _myPostList.value = responseMyPostList.result
            } catch (e: HttpException) {
                Timber.d(e.message().toString())
            }
        }
    }

    fun postReport(flag: Int, number: Int, text: String) {
        _setLoading.postValue(true)
        viewModelScope.launch {
            try {
                val responseReport = ServiceBuilder.reportService.postReport(
                    RequestReport(flag, number, text)
                )
                _setLoading.postValue(false)
                Timber.d(responseReport.message)
                _isSuccessReport.value = responseReport.code == 1000
            } catch (e: HttpException) {
                Timber.d(e.message().toString())
                _isSuccessReport.value = false
            }
        }
    }

    fun initSuccessReport() {
        _isSuccessReport = MutableLiveData<Boolean>()
        isSuccessReport = _isSuccessReport

        _setLoading = MutableLiveData<Boolean>()
        setLoading = _setLoading
    }
}