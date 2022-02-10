package com.mentos.mentosandroid.ui.search

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.util.SharedPreferenceController
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class SearchViewModel : ViewModel() {

    val createTitle = MutableLiveData("")
    val createContent = MutableLiveData("")
    lateinit var imageMultiPart: MultipartBody.Part
    private val map = mutableMapOf<String, @JvmSuppressWildcards RequestBody>()

    val createCategory = MutableLiveData<Int>()

    private val _firstCategory = MutableLiveData(0)
    val firstCategory: LiveData<Int> = _firstCategory

    private val _secondCategory = MutableLiveData(0)
    val secondCategory: LiveData<Int> = _secondCategory

    private val _searchMentorList = MutableLiveData<List<SearchMentor>>()
    val searchMentorList: LiveData<List<SearchMentor>> = _searchMentorList

    private val _searchCategory = MutableLiveData<List<Int>>()
    val searchCategory: LiveData<List<Int>> = _searchCategory

    private val _firstCategoryClicked = MutableLiveData<Boolean>()
    val firstCategoryClicked: LiveData<Boolean> = _firstCategoryClicked

    private val _secondCategoryClicked = MutableLiveData<Boolean>()
    val secondCategoryClicked: LiveData<Boolean> = _secondCategoryClicked

    private val _allCategoryClicked = MutableLiveData<Boolean>()
    val allCategoryClicked: LiveData<Boolean> = _allCategoryClicked

    private val _searchMenteeList = MutableLiveData<List<Mentee>>()
    val searchMenteeList: LiveData<List<Mentee>> = _searchMenteeList

    private val tempSearchCategory = mutableListOf<Int>()

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
        viewModelScope.launch {
            try {
                val responseSearchMentor = ServiceBuilder.searchService.getSearchMentor(
                    majorFlag = requireNotNull(searchCategory.value),
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

    fun requestMenteeList(searchText: String) {
        _searchMenteeList.value = arrayListOf(
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2),
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2),
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2),
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2),
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2),
            Mentee(1, "", "경영학과", 4, "18", "호옹길도옹", 2)
        )
        viewModelScope.launch {
            try {
                val responseSearchMentor = ServiceBuilder.searchService.getSearchMentee(
                    majorFlag = requireNotNull(searchCategory.value),
                    searchText = searchText
                )
                _searchMenteeList.postValue(responseSearchMentor.result.mentiArr)
            } catch (e: HttpException) {
                _searchMentorList.postValue(listOf())
                Log.d("찾기", e.message().toString())
                Log.d("찾기", e.code().toString())
            }
        }

    }

    fun getMentosCategoryForFirst() {
        Log.d("검색-카테고리api", "first 호출됨")
        viewModelScope.launch {
            try {
                val responseCategory = ServiceBuilder.searchService.getSearchCategory(
                    SharedPreferenceController.getNowState()
                )
                if (responseCategory.isSuccess) {
                    _firstCategory.value = responseCategory.result.first
                    _secondCategory.value = responseCategory.result.second
                    setFirstCategoryClick(true)
                    setSecondCategoryClick(false)
                    setAllCategoryClick(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun getMentosCategoryForSecond() {
        Log.d("검색-카테고리api", "second 호출됨")
        viewModelScope.launch {
            try {
                val responseCategory = ServiceBuilder.searchService.getSearchCategory(
                    SharedPreferenceController.getNowState()
                )
                if (responseCategory.isSuccess) {
                    _firstCategory.value = responseCategory.result.first
                    _secondCategory.value = responseCategory.result.second
                    setSecondCategoryClick(true)
                    setAllCategoryClick(false)
                    setFirstCategoryClick(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun getMentosCategoryForAll() {
        Log.d("검색-카테고리api", "all 호출됨")
        viewModelScope.launch {
            try {
                val responseCategory = ServiceBuilder.searchService.getSearchCategory(
                    SharedPreferenceController.getNowState()
                )
                if (responseCategory.isSuccess) {
                    _firstCategory.value = responseCategory.result.first
                    _secondCategory.value = responseCategory.result.second
                    setAllCategoryClick(true)
                    setSecondCategoryClick(false)
                    setFirstCategoryClick(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun postContent() {
        viewModelScope.launch {
            try {
                setMultiPart()
                val responseSearchCreate =
                    when (image.value) {
                        null -> {
                            ServiceBuilder.searchService.postSearchCreate(
                                map,
                                null
                            )
                        }
                        else -> {
                            ServiceBuilder.searchService.postSearchCreate(
                                map,
                                imageMultiPart
                            )
                        }
                    }
                if(responseSearchCreate.isSuccess) {
                    _isRegister.postValue(true)
                }
            } catch (e: HttpException) { }
        }
    }

    fun setMultiPart() {
        map["majorCategoryId"] =
            RequestBody.create(MediaType.parse("text/plain"), createCategory.value.toString())
        map["postTitle"] =
            RequestBody.create(MediaType.parse("text/plain"), requireNotNull(createTitle.value))

        map["postContents"] =
            RequestBody.create(MediaType.parse("text/plain"), requireNotNull(createContent.value))
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

    fun setSearchCategory(category: Int) {
        tempSearchCategory.add(category)
        _searchCategory.value = tempSearchCategory.toMutableList()
    }

    fun clearSearchCategory() {
        tempSearchCategory.clear()
        _searchCategory.value = tempSearchCategory.toMutableList()
    }

    fun setFirstCategoryClick(isClick: Boolean) {
        _firstCategoryClicked.value = isClick
    }

    fun setSecondCategoryClick(isClick: Boolean) {
        _secondCategoryClicked.value = isClick
    }

    fun setAllCategoryClick(isClick: Boolean) {
        _allCategoryClicked.value = isClick
    }
}