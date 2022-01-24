package com.mentos.mentosandroid.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Search

class SearchViewModel : ViewModel() {

    private val _dummyList = MutableLiveData<List<Search>>()
    val dummyList: LiveData<List<Search>> = _dummyList

    fun requestEvent() {
        _dummyList.value = listOf(
            Search("제목 1", 0, 100),
            Search("제목 2", 1, 200),
            Search("제목 2", 3, 200),
            Search("제목 31", 4, 200),
            Search("제목 3", 5, 0),
            Search("프랑스어과 17학번 재학생입니다. 프랑스 문화, 회화, 쓰기, 읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모", 6, 0),
            Search("제목 2", 10, 0),
            Search("제목 31", 11, 0),
            Search("제목 3", 12, 0),
            Search("제목 14", 13, 0)
        )
    }
}