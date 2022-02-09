package com.mentos.mentosandroid.ui.findpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestFindPw
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import kotlinx.coroutines.launch
import retrofit2.HttpException

class FindPwViewModel : ViewModel() {
    val email = MutableLiveData("")

    private val _isSuccessFindPw = MutableLiveData<Boolean>()
    val isSuccessFindPw: LiveData<Boolean> = _isSuccessFindPw

    private val _setLoading = MutableLiveData<Boolean>()
    val setLoading: LiveData<Boolean> = _setLoading

    private val _canFindPw = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(email)
    ) { requireNotNull(email.value).isNotBlank() }
    val canFindPw: LiveData<Boolean> = _canFindPw

    fun postFindPw() {
        setLoadingState(true)
        viewModelScope.launch {
            try {
                val responseFindPw = ServiceBuilder.authService.postFindPw(
                    RequestFindPw(
                        requireNotNull(email.value)
                    )
                )
                setLoadingState(false)
                when (responseFindPw.isSuccess) {
                    true -> setSuccessFindPw(true)
                    else -> setSuccessFindPw(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun setSuccessFindPw(isSuccess: Boolean) {
        _isSuccessFindPw.value = isSuccess
    }

    fun setLoadingState(isLoading: Boolean) {
        _setLoading.value = isLoading
    }
}