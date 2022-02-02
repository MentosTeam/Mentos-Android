package com.mentos.mentosandroid.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class SignInViewModel : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _canSignIn = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(email, password)
    ) { canSignInCheck() }
    val canSignIn: LiveData<Boolean> = _canSignIn

    private val _canAutoSignIn = MutableLiveData(canSignInCheck())
    val canAutoSignIn: LiveData<Boolean> = _canAutoSignIn

    private val _isSuccessSignIn = MutableLiveData<Boolean>()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn

    private fun canSignInCheck() =
        requireNotNull(email.value).isNotBlank()
                && requireNotNull(password.value).isNotBlank()

    fun postSignIn() {
        setSuccessSignIn(true)
    }

    fun setSuccessSignIn(isSuccess : Boolean) {
        _isSuccessSignIn.value = isSuccess
    }
}