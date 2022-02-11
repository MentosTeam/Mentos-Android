package com.mentos.mentosandroid.ui.mentoringstate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestReview
import com.mentos.mentosandroid.data.request.RequestStateRecord
import com.mentos.mentosandroid.data.response.StateEndMentee
import com.mentos.mentosandroid.data.response.StateEndMentor
import com.mentos.mentosandroid.data.response.StateNow
import com.mentos.mentosandroid.data.response.StateRecord
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import kotlinx.coroutines.launch
import retrofit2.HttpException

class StateViewModel : ViewModel() {

    val recordContent = MutableLiveData("")

    private val _nowList = MutableLiveData<List<StateNow>>()
    val nowList: LiveData<List<StateNow>> = _nowList

    private val _endMentorList = MutableLiveData<List<StateEndMentor>>()
    val endMentorList: LiveData<List<StateEndMentor>> = _endMentorList

    private val _endMenteeList = MutableLiveData<List<StateEndMentee>>()
    val endMenteeList: LiveData<List<StateEndMentee>> = _endMenteeList

    private val _beforeList = MutableLiveData<List<StateNow>>()
    val beforeList: LiveData<List<StateNow>> = _beforeList

    private val _recordList = MutableLiveData<List<StateRecord>>()
    val recordList: LiveData<List<StateRecord>> = _recordList

    private val _isLastRecord = MutableLiveData<Boolean>()
    val isLastRecord: LiveData<Boolean> = _isLastRecord

    private val _canRecord = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(recordContent)
    ) { requireNotNull(recordContent.value).isNotBlank() }
    val canRecord: LiveData<Boolean> = _canRecord

    fun getStateMentorList() {
        viewModelScope.launch {
            try {
                val responseMentor = ServiceBuilder.stateService.getMentorState()

                _nowList.postValue(responseMentor.result.nowMentoring)
                _endMentorList.postValue(responseMentor.result.endMentoring)
                _beforeList.postValue(responseMentor.result.beforeMentoring)
            } catch (e: HttpException) {
                _nowList.postValue(listOf())
                _endMentorList.postValue(listOf())
                _beforeList.postValue(listOf())
                Log.d("현황-멘토 실패", e.message.toString())
            }
        }
    }

    fun getStateMenteeList() {
        viewModelScope.launch {
            try {
                val responseMentee = ServiceBuilder.stateService.getMenteeState()

                _nowList.postValue(responseMentee.result.nowMentoring)
                _endMenteeList.postValue(responseMentee.result.endMentoring)
                _beforeList.postValue(responseMentee.result.beforeMentoring)
            } catch (e: HttpException) {
                _nowList.postValue(listOf())
                _endMentorList.postValue(listOf())
                _beforeList.postValue(listOf())
                Log.d("현황-멘티 실패", e.message.toString())
            }
        }
    }

    fun getRecordList(mentoringId: Int) {
        viewModelScope.launch {
            try {
                val responseGetRecord = ServiceBuilder.stateService.getRecord(mentoringId)
                _recordList.postValue(responseGetRecord.result.reports)
            } catch (e: HttpException) {
                _recordList.postValue(listOf())
                Log.d("일지 조회-실패", e.message.toString())
            }
        }
    }

    fun postRecord(mentoringId: Int) {
        viewModelScope.launch {
            try {
                Log.d("일지 등록-id", mentoringId.toString())
                Log.d("일지 등록-기록", recordContent.value.toString())
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
                Log.d("일지 등록-실패", e.message.toString())
            }
        }
    }

    fun postReview(mentoringId: Int, reviewScore: Double, reviewText: String) {
        viewModelScope.launch {
            try {
                Log.d("리뷰 등록- id", mentoringId.toString())
                Log.d("리뷰 등록- 점수", reviewScore.toString())
                Log.d("리뷰 등록- 내용", reviewText)
                ServiceBuilder.stateService.postReview(
                    mentoringId,
                    RequestReview(
                        mentoringId,
                        reviewScore,
                        reviewText
                    )
                )
            } catch (e: HttpException) {
                Log.d("리뷰 등록-실패", e.message.toString())
            }
        }
    }
}