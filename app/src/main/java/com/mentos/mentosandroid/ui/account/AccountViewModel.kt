package com.mentos.mentosandroid.ui.account

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.local.ChatProfile
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.HttpException

class AccountViewModel : ViewModel() {
    val introduce = MutableLiveData("")
    lateinit var imageMultiPart: MultipartBody.Part
    private val map = mutableMapOf<String, @JvmSuppressWildcards RequestBody>()

    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    private val _selectedCategory = MutableLiveData<List<Int>>()
    val selectedCategory: LiveData<List<Int>> = _selectedCategory

    private val tempCategory = mutableListOf<Int>()

    private val _selectedState = MutableLiveData<Int>()
    val selectedState: LiveData<Int> = _selectedState

    private val _isSuccessCreate = MutableLiveData<Boolean>()
    val isSuccessCreate: LiveData<Boolean> = _isSuccessCreate

    private val _canRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(introduce)
    ) { canRegisterCheck() }
    val canRegister: LiveData<Boolean> = _canRegister

    private fun canRegisterCheck() =
        requireNotNull(introduce.value).isNotBlank()
                && introduce.value!!.length > 9

    fun setMultiPart() {
        map["role"] =
            RequestBody.create(MediaType.parse("text/plain"), selectedState.value.toString())
        map["introduction"] =
            RequestBody.create(MediaType.parse("text/plain"), requireNotNull(introduce.value))
        when (selectedCategory.value!!.size) {
            1 -> {
                map["majorFirst"] =
                    RequestBody.create(MediaType.parse("text/plain"), tempCategory[0].toString())
                map["majorSecond"] = RequestBody.create(MediaType.parse("text/plain"), "0")
            }
            2 -> {
                map["majorFirst"] =
                    RequestBody.create(MediaType.parse("text/plain"), tempCategory[0].toString())
                map["majorSecond"] =
                    RequestBody.create(MediaType.parse("text/plain"), tempCategory[1].toString())
            }
        }
    }

    fun postCreateProfile() {
        viewModelScope.launch {
            try {
                setMultiPart()
                val responseCreateProfile = ServiceBuilder.profileService.postCreateProfile(
                    map,
                    imageFile = when (image.value) {
                        null -> null
                        else -> imageMultiPart
                    }
                )
                if (responseCreateProfile.isSuccess) {
                    setSuccessCreate(true)
                    Firebase.database.reference.child("profile")
                        .child(responseCreateProfile.result.memberId.toString()).setValue(
                            ChatProfile(
                                memberId = responseCreateProfile.result.memberId.toString(),
                                profileImage = responseCreateProfile.result.profileImgUrl,
                                nickname = responseCreateProfile.result.nickname
                            )
                        )
                } else {
                    setSuccessCreate(false)
                }
            } catch (e: HttpException) {
                Log.d("생성실패", e.code().toString())
            }
        }
    }

    fun setCategory(category: Int) {
        tempCategory.add(category)
        _selectedCategory.value = tempCategory.toMutableList()
    }

    fun removeCategory(category: Int) {
        tempCategory.remove(category)
        _selectedCategory.value = tempCategory.toMutableList()
    }

    fun setState(state: Int) {
        _selectedState.value = state
    }

    fun setImage(imgUri: Uri) {
        _image.value = imgUri
    }

    fun setSuccessCreate(isSuccess: Boolean) {
        _isSuccessCreate.value = isSuccess
    }
}