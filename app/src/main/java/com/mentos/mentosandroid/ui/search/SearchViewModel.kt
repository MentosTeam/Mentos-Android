package com.mentos.mentosandroid.ui.search

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.Mentee
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.util.SharedPreferenceController
import kotlinx.coroutines.launch
import retrofit2.HttpException

class SearchViewModel : ViewModel() {

    val createTitle = MutableLiveData("")
    val createContent = MutableLiveData("")

    private val _searchMentorList = MutableLiveData<List<SearchMentor>>()
    val searchMentorList: LiveData<List<SearchMentor>> = _searchMentorList

    private val _searchCategory = MutableLiveData<List<Int>>()
    val searchCategory: LiveData<List<Int>> = _searchCategory

    private val _dummyMenteeList = MutableLiveData<List<Mentee>>()
    val dummyMenteeList: LiveData<List<Mentee>> = _dummyMenteeList

    private val _isCategorySelected = MutableLiveData(false)
    val isCategorySelected: LiveData<Boolean> = _isCategorySelected

    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean> = _isRegister

    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    private val _isWritten = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            createTitle,
            createContent,
            isCategorySelected
        )
    ) { isWrittenCheck() }
    val isWritten: LiveData<Boolean> = _isWritten

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

    private fun isWrittenCheck() =
        requireNotNull(createContent.value).isNotBlank()
                || requireNotNull(createTitle.value).isNotBlank()
                || requireNotNull(isCategorySelected.value)

    fun getMentorPostList(searchText: String) {
        Log.d("찾기", SharedPreferenceController.getJwtToken().toString())
        viewModelScope.launch {
            try {
                val responseSearchMentor = ServiceBuilder.searchService.getSearchMentor(
                    majorFlag = mutableListOf(1),
                    searchText = searchText
                )
                _searchMentorList.postValue(responseSearchMentor.result.postArr)
            } catch (e: HttpException) {
                _searchMentorList.postValue(listOf())
                Log.d("찾기", e.message().toString())
                Log.d("찾기", e.code().toString())
            }
        }
    }

    fun requestMenteeList() {
        _dummyMenteeList.value = arrayListOf(
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
            Mentee(1, "홍길동", "경영학과", "18학번", "", 1, 2),
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

    fun setCreateTitle(title: String) {
        createTitle.value = title
    }

    fun setCreateContent(content: String) {
        createContent.value = content
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

    fun setIsWritten(isWritten: Boolean) {
        _isWritten.value = isWritten
    }
}