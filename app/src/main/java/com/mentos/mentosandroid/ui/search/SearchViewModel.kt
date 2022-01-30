package com.mentos.mentosandroid.ui.search

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Profile
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class SearchViewModel : ViewModel() {

    //searchCreate
    val createTitle = MutableLiveData("")
    val createContent = MutableLiveData("")

    private val _isCategorySelected = MutableLiveData(false)
    val isCategorySelected: LiveData<Boolean> = _isCategorySelected

    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean> = _isRegister

    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    private val _canRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            createTitle,
            createContent,
            isCategorySelected
        )
    ) { canRegisterCheck() }
    val canRegister: LiveData<Boolean> = _canRegister

    private fun canRegisterCheck() =
        requireNotNull(createContent.value).isNotBlank()
                && requireNotNull(createTitle.value).isNotBlank()
                && requireNotNull(isCategorySelected.value)

    private val _dummyList = MutableLiveData<List<Search>>()
    val dummyList: LiveData<List<Search>> = _dummyList

    private val _dummyMenteeList = MutableLiveData<List<Profile>>()
    val dummyMenteeList: LiveData<List<Profile>> = _dummyMenteeList

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

    fun requestMenteeList() {
        _dummyMenteeList.value = listOf(
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문"),
            Profile("", "홍길동 / 경영학과 / 19학번", "#경제/경영, #어문")
        )
    }

    //searchCreate
    fun postContent() {
        //network
        _isRegister.postValue(true)
    }

    fun resetIsRegister() {
        _isRegister.value = false
    }

    fun setImage(imgUri: Uri) {
        _image.value = imgUri
    }

    fun resetImage() {
        _image.value = null
    }

    fun setCategory(isSelected: Boolean) {
        _isCategorySelected.value = isSelected
    }
}