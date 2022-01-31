package com.mentos.mentosandroid.ui.myprofiledetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Search

class PostListViewModel : ViewModel() {

    private val _dummyList = MutableLiveData<List<Search>>()
    val dummyList: LiveData<List<Search>> = _dummyList

    fun requestEvent() {
        _dummyList.value = listOf(
            Search("제목 1", 0, 1, null, "태현", "내용입니다1", 1),
            Search("제목 2", 1, 1, "dd", "가은", "내용입니다1내용입니다1내용입니다1", 1),
            Search("제목 2", 3, 1, null, "태현", "내용입니다1", 1),
            Search("제목 31", 4, 1, null, "태현", "내용입니다1", 1),
            Search("제목 3", 5, 1, null, "태현", "내용입니다1", 1),
            Search(
                "프랑스어과 17학번 재학생입니다. 프랑스 문화, 회화, 쓰기, 읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모",
                6,
                1,
                null,
                "태현",
                "내용입니다1",
                1
            ),
            Search("제목 2", 10, 1, null, "태현", "내용입니다1", 1),
            Search("제목 31", 11, 1, null, "태현", "내용입니다1", 1),
            Search("제목 3", 12, 1, null, "태현", "내용입니다1", 1),
            Search("제목 14", 13, 1, null, "태현", "내용입니다1", 1)
        )
    }
}