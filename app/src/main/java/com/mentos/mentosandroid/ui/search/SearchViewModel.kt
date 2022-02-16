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
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class SearchViewModel : ViewModel() {

    val searchText = MutableLiveData("")
    val createTitle = MutableLiveData("")
    val createContent = MutableLiveData("")
    val createCategory = MutableLiveData<Int>()
    lateinit var imageMultiPart: MultipartBody.Part
    private val map = mutableMapOf<String, @JvmSuppressWildcards RequestBody>()

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

    private val _isCategoryClicked = MutableLiveData<Boolean>()
    val isCategoryClicked: LiveData<Boolean> = _isCategoryClicked

    private val _searchMenteeList = MutableLiveData<List<Mentee>>()
    val searchMenteeList: LiveData<List<Mentee>> = _searchMenteeList

    private val tempSearchCategory = mutableListOf<Int>()

    private val _isCategorySelected = MutableLiveData(false)
    val isCategorySelected: LiveData<Boolean> = _isCategorySelected

    private val _isRegister = MutableLiveData<Boolean>()
    val isRegister: LiveData<Boolean> = _isRegister

    private val _isDeletedSuccess = MutableLiveData<Boolean>()
    val isDeletedSuccess: LiveData<Boolean> = _isDeletedSuccess

    private val _isModifySuccess = MutableLiveData<Boolean>()
    val isModifySuccess: LiveData<Boolean> = _isModifySuccess

    private val _hasImage = MutableLiveData(false)
    val hasImage: LiveData<Boolean> = _hasImage

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

    fun getMentorPostList(searchChangeText: String?) {
        viewModelScope.launch {
            try {
                val responseSearchMentor = ServiceBuilder.searchService.getSearchMentor(
                    majorFlag = requireNotNull(searchCategory.value),
                    searchText = when (isCategoryClicked.value) {
                        true -> searchText.value
                        else -> searchChangeText
                    }
                )
                _searchMentorList.postValue(responseSearchMentor.result.postArr)
            } catch (e: HttpException) {
                _searchMentorList.postValue(listOf())
                Log.d("찾기", e.message().toString())
            }
        }
    }

    fun getMenteeList(searchChangeText: String?) {
        viewModelScope.launch {
            try {
                val responseSearchMentor = ServiceBuilder.searchService.getSearchMentee(
                    majorFlag = requireNotNull(searchCategory.value),
                    searchText = when (isCategoryClicked.value) {
                        true -> searchText.value
                        else -> searchChangeText
                    }
                )
                _searchMenteeList.postValue(responseSearchMentor.result.mentiArr)
            } catch (e: HttpException) {
                _searchMentorList.postValue(listOf())
                Log.d("찾기", e.message().toString())
            }
        }
    }

    fun getMentosCategoryForFirst() {
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
                val responseSearchCreate = ServiceBuilder.searchService.postSearchCreate(
                    map,
                    when (image.value) {
                        null -> null
                        else -> imageMultiPart
                    }
                )
                if (responseSearchCreate.isSuccess) {
                    _isRegister.postValue(true)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun deleteContent(postId: Int) {
        viewModelScope.launch {
            try {
                val responseDeleteContent = ServiceBuilder.searchService.deleteMentorPost(
                    postId
                )
                when (responseDeleteContent.isSuccess) {
                    true -> _isDeletedSuccess.postValue(true)
                    false -> _isDeletedSuccess.postValue(false)
                }
            } catch (e: HttpException) {
                Log.d("글 삭제", e.message.toString())
            }
        }
    }

    fun modifyContent(postId: Int, hasImage: Boolean, imageUrl: String) {
        viewModelScope.launch {
            try {
                when (hasImage) {
                    true -> setMultiPartForModify(imageUrl)
                    false -> setMultiPart()
                }
                val responseModifyContent = ServiceBuilder.searchService.modifyMentorPost(
                    postId,
                    map,
                    when (image.value) {
                        null -> null
                        else -> imageMultiPart
                    }
                )
                when (responseModifyContent.isSuccess) {
                    true -> _isModifySuccess.postValue(true)
                    false -> _isModifySuccess.postValue(false)
                }
            } catch (e: HttpException) {
                Log.d("글 수정", e.message.toString())
            }
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

    private fun setMultiPartForModify(imageUrl: String) {
        map["majorCategoryId"] =
            RequestBody.create(MediaType.parse("text/plain"), createCategory.value.toString())
        map["postTitle"] =
            RequestBody.create(MediaType.parse("text/plain"), requireNotNull(createTitle.value))
        map["postContents"] =
            RequestBody.create(MediaType.parse("text/plain"), requireNotNull(createContent.value))
        map["imageUrl"] = RequestBody.create(MediaType.parse("text/plain"), imageUrl)
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

    fun setModifyHasImage(state: Boolean) {
        _hasImage.value = state
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

    fun isCategoryClicked(isClick: Boolean) {
        _isCategoryClicked.value = isClick
    }
}