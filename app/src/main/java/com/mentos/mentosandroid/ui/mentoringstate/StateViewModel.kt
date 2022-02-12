package com.mentos.mentosandroid.ui.mentoringstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestReview
import com.mentos.mentosandroid.data.request.RequestStateRecord
import com.mentos.mentosandroid.data.response.*
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StateViewModel : ViewModel() {

    val recordContent = MutableLiveData("")

    private val _nowList = MutableLiveData<List<StateNow>>()
    val nowList: LiveData<List<StateNow>> = _nowList

    private val _endList = MutableLiveData<List<StateEnd>>()
    val endList: LiveData<List<StateEnd>> = _endList

    private val _waitList = MutableLiveData<List<StateWait>>()
    val waitList: LiveData<List<StateWait>> = _waitList

    private val _recordList = MutableLiveData<List<StateRecord>>()
    val recordList: LiveData<List<StateRecord>> = _recordList

    private val _isLastRecord = MutableLiveData<Boolean>()
    val isLastRecord: LiveData<Boolean> = _isLastRecord

    private val tempRecordList = mutableListOf<StateRecord>()

    private val _canRecord = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(recordContent)
    ) { requireNotNull(recordContent.value).isNotBlank() }
    val canRecord: LiveData<Boolean> = _canRecord

    fun getStateMentorList() {
        viewModelScope.launch {
            try {
                val responseMentor = ServiceBuilder.stateService.getMentorState()
                _nowList.postValue(responseMentor.result.nowMentoring)
                _endList.postValue(responseMentor.result.endMentoring)
                _waitList.postValue(responseMentor.result.waitMentoring)
            } catch (e: HttpException) {
                _nowList.postValue(listOf())
                _endList.postValue(listOf())
                _waitList.postValue(listOf())
            }
        }
    }

    fun getStateMenteeList() {
        viewModelScope.launch {
            try {
                val responseMentee = ServiceBuilder.stateService.getMenteeState()
                _nowList.postValue(responseMentee.result.nowMentoring)
                _endList.postValue(responseMentee.result.endMentoring)
                _waitList.postValue(responseMentee.result.waitMentoring)
            } catch (e: HttpException) {
                _nowList.postValue(listOf())
                _endList.postValue(listOf())
                _waitList.postValue(listOf())
            }
        }
    }

    fun getRecordList(mentoringId: Int) {
        viewModelScope.launch {
            try {
                val responseGetRecord = ServiceBuilder.stateService.getRecord(mentoringId)
                for (i in responseGetRecord.result.reports.indices) {
                    tempRecordList.add(
                        i,
                        responseGetRecord.result.reports[responseGetRecord.result.reports.size - (1 + i)]
                    )
                }
                _recordList.value = tempRecordList.toMutableList()

            } catch (e: HttpException) {
                _recordList.postValue(listOf())
            }
        }
    }

    fun postRecord(mentoringId: Int) {
        viewModelScope.launch {
            try {
                val responsePostRecord = ServiceBuilder.stateService.postRecord(
                    RequestStateRecord(
                        mentoringId = mentoringId,
                        report = requireNotNull(recordContent.value)
                    )
                )
                if (responsePostRecord.isSuccess) {
                    when (responsePostRecord.result) {
                        1 -> _isLastRecord.postValue(false)
                        2 -> _isLastRecord.postValue(true)
                    }
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun postReview(mentoringId: Int, reviewScore: Double, reviewText: String) {
        viewModelScope.launch {
            try {
                ServiceBuilder.stateService.postReview(
                    mentoringId,
                    RequestReview(
                        mentoringId,
                        reviewScore,
                        reviewText
                    )
                )
            } catch (e: HttpException) {
            }
        }
    }
}