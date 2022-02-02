package com.mentos.mentosandroid.ui.findpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class FindPwViewModel : ViewModel() {
    val email = MutableLiveData("")

    private val _isSuccessFindPw = MutableLiveData<Boolean>()
    val isSuccessFindPw :LiveData<Boolean> = _isSuccessFindPw

    private val _canFindPw = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(email)
    ) { requireNotNull(email.value).isNotBlank() }
    val canFindPw: LiveData<Boolean> = _canFindPw

    fun postFindPw() {
        setSuccessFindPw(true)
    }

    fun setSuccessFindPw(isSuccess:Boolean) {
        _isSuccessFindPw.value = isSuccess
    }

}