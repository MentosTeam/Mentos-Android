package com.mentos.mentosandroid.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class AccountViewModel : ViewModel() {
    val introduce = MutableLiveData("")

    private val _canRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            introduce
        )
    ) { requireNotNull(introduce.value).isNotBlank() }
    val canRegister: LiveData<Boolean> = _canRegister
}