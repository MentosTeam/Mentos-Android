package com.mentos.mentosandroid.ui.search

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Search
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class SearchViewModel : ViewModel() {

    //searchCreate
    val createTitle = MutableLiveData("")
    val createContent = MutableLiveData("")

    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean> = _isRegister

    private val _image = MutableLiveData<Uri>()
    val image: LiveData<Uri> = _image

    private val _canRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            createTitle,
            createContent
        )
    ) { canRegisterCheck() }
    val canRegister: LiveData<Boolean> = _canRegister

    private fun canRegisterCheck() =
        requireNotNull(createContent.value).isNotBlank() && requireNotNull(createTitle.value).isNotBlank()

    private val _dummyList = MutableLiveData<List<Search>>()
    val dummyList: LiveData<List<Search>> = _dummyList

    fun requestEvent() {
        _dummyList.value = listOf(
            Search("제목 1", 0, 100),
            Search("제목 2", 1, 200),
            Search("제목 2", 3, 200),
            Search("제목 31", 4, 200),
            Search("제목 3", 5, 0),
            Search(
                "프랑스어과 17학번 재학생입니다. 프랑스 문화, 회화, 쓰기, 읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모읽기 관련 강의 모",
                6,
                0
            ),
            Search("제목 2", 10, 0),
            Search("제목 31", 11, 0),
            Search("제목 3", 12, 0),
            Search("제목 14", 13, 0)
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
}