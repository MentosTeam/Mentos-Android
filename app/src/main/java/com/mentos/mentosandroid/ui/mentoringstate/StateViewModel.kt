package com.mentos.mentosandroid.ui.mentoringstate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.StateEnd
import com.mentos.mentosandroid.data.StateNow
import com.mentos.mentosandroid.data.StateRecord
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class StateViewModel : ViewModel() {

    val recordContent = MutableLiveData("")

    private val _dummyNowList = MutableLiveData<List<StateNow>>()
    val dummyNowList: LiveData<List<StateNow>> = _dummyNowList

    private val _dummyEndList = MutableLiveData<List<StateEnd>>()
    val dummyEndList: LiveData<List<StateEnd>> = _dummyEndList

    private val _dummyRecordList = MutableLiveData<List<StateRecord>>()
    val dummyRecordList: LiveData<List<StateRecord>> = _dummyRecordList

    private val _isSuccessRecord = MutableLiveData<Boolean>()
    val isSuccessRecord: LiveData<Boolean> = _isSuccessRecord

    private val _canRecord = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            recordContent
        )
    ) { requireNotNull(recordContent.value).isNotBlank() }
    val canRecord: LiveData<Boolean> = _canRecord

    fun postRecord() {
        setIsSuccessRecord(true)
    }

    fun setIsSuccessRecord(isSuccess: Boolean) {
        _isSuccessRecord.value = isSuccess
    }

    fun requestNowList() {
        _dummyNowList.value = arrayListOf(
            StateNow(0, 2, 3, 5, 7, "가은", "현진"),
            StateNow(1, 9, 3, 5, 7, "가은", "현진"),
            StateNow(2, 3, 3, 5, 7, "가은", "현진"),
            StateNow(3, 2, 3, 5, 7, "가은", "현진"),
            StateNow(4, 5, 3, 5, 7, "가은", "현진"),
            StateNow(5, 11, 3, 5, 7, "가은", "현진"),
            StateNow(6, 7, 3, 5, 7, "가은", "현진"),
        )
    }

    fun requestEndList() {
        _dummyEndList.value = arrayListOf(
            StateEnd(0, 2, 3, 5, 7, "가은", "현진", false),
            StateEnd(1, 9, 3, 5, 7, "가은", "현진", false),
            StateEnd(2, 3, 3, 5, 7, "가은", "현진", false),
            StateEnd(3, 2, 3, 5, 7, "가은", "현진", true),
            StateEnd(4, 5, 3, 5, 7, "가은", "현진", true),
            StateEnd(5, 11, 3, 5, 7, "가은", "현진", true),
            StateEnd(6, 7, 3, 5, 7, "가은", "현진", true),
        )
    }

    fun requestRecordList() {
        _dummyRecordList.value = arrayListOf(
            StateRecord(
                1,
                2,
                5,
                "22/12/13",
                "중어중문학과 16학번 홍길동입니다. 기초 중국어 영역에 도움을 줄 수 있어요!  3년 동안 학생회에 참여해 관련 정보도 드릴 수 있습니다! 멘토와 대화하기 버튼을 눌러서 편하게 대화해봐요! 언제든지 환영입니다~중어중문학과 16학번 홍길동입니다. 기초 중국어 영역에 도움을 줄 수 있어요!"
            ),
            StateRecord(
                1,
                2,
                4,
                "22/12/13",
                "중어중문학과 16학번 홍길동입니다. 기초 중국어 영역에 도움을 줄 수 있어요!  3년 동안 학생회에 참여해 관련 정보도 드릴 수 있습니다! 멘토와 대화하기 버튼을 눌러서 편하게 대화해봐요! 언제든지 환영입니다~중어중문학과 16학번 홍길동입니다. 기초 중국어 영역에 도움을 줄 수 있어요!"
            ),
            StateRecord(
                1,
                2,
                3,
                "22/12/13",
                "멘토링 완료"
            )
        )
    }
}