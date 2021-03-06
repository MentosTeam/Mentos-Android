package com.mentos.mentosandroid.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.local.ChatBubble
import com.mentos.mentosandroid.data.local.ChatList
import com.mentos.mentosandroid.data.request.RequestReport
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class ChatViewModel : ViewModel() {
    val newMsg = MutableLiveData("")

    private val tempChatBubble = mutableListOf<ChatBubble>()
    private val tempChatList = mutableListOf<ChatList>()

    private val _chatRoomKey = MutableLiveData<String?>()
    val chatRoomKey: LiveData<String?> = _chatRoomKey

    private val _chatBubbleList = MutableLiveData<List<ChatBubble>>()
    val chatBubbleList: LiveData<List<ChatBubble>> = _chatBubbleList

    private val _chatList = MutableLiveData<List<ChatList>>()
    val chatList: LiveData<List<ChatList>> = _chatList

    private var _isSuccessReport = MutableLiveData<Boolean>()
    var isSuccessReport: LiveData<Boolean> = _isSuccessReport

    private var _setLoading = MutableLiveData<Boolean>()
    var setLoading: LiveData<Boolean> = _setLoading

    fun addChatBubbleList(item: ChatBubble) {
        tempChatBubble.add(item)
        _chatBubbleList.value = tempChatBubble.toMutableList()
    }

    fun clearChatBubbleList() {
        tempChatBubble.clear()
        _chatBubbleList.value = tempChatBubble.toMutableList()
    }

    fun addChatList(item: ChatList) {
        tempChatList.add(item)
        _chatList.value = tempChatList.toMutableList()
    }

    fun clearChatList() {
        tempChatList.clear()
        _chatList.value = tempChatList.toMutableList()
    }

    fun setRoomKey(roomKey: String) {
        _chatRoomKey.value = roomKey
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
}