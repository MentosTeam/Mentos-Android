package com.mentos.mentosandroid.ui.notification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Notification

class NotificationViewModel : ViewModel() {

    private val _notiList = MutableLiveData<ArrayList<Notification>>()
    val notiList: LiveData<ArrayList<Notification>>
        get() = _notiList
    private lateinit var notiItems: ArrayList<Notification>

    init {
        getNotification()

    }

    private fun getNotification() {
        notiItems = arrayListOf(
            Notification(
                "2월 4일",
                "멘토님 안녕하세요, 멘토-쓰 운영진입니다!\n멘토-쓰 사용 방법을 알려드릴게요!\n\n- 멘토는 멘티에게 멘토링 하며 멘토-쓰를 얻을 수 있어요.\n(대충 이런내용)n대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n(대충 이런내용)\n\n오늘도 멘토-쓰를 통해\n당신의 지식이 가치있게 쓰이길 바랍니다!"
            ),
            Notification("2월 3일", "멘티의 멘토링 요청이 도착했어요!\n자세한 사항은 멘토링 현황에서 확인해 주세요"),
            Notification(
                "2월 2일",
                "멘토링이 종료되었습니다!\n별점 및 후기 남기기 잊지마세요~!\n멘토링 진행 중 문제가 생겼다면, 내정보 하단 버튼을 통해 멘토-쓰 운영진에게 연락주세요!"
            )
        )
        _notiList.value = notiItems
    }

}
