package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.response.SearchMentor

class PostListViewModel : ViewModel() {

    private val _dummyList = MutableLiveData<List<SearchMentor>>()
    val dummyList: LiveData<List<SearchMentor>> = _dummyList

    fun requestEvent() {
        _dummyList.value = listOf(
            SearchMentor(null, 0, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 1, "사회학과", 2, "가은", "내용입니다1내용입니다1내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 3, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 4, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 5, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 6, "사회학과", 1, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 10, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 11, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 12, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다"),
            SearchMentor(null, 13, "사회학과", 2, "태현", "내용입니다1", 1, "제목입니다")
        )
    }
}