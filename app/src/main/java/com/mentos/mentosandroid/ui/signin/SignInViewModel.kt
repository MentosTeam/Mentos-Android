package com.mentos.mentosandroid.ui.signin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestSignIn
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.util.SharedPreferenceController
import kotlinx.coroutines.launch
import retrofit2.HttpException

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
        viewModelScope.launch {
            try {
                val responseSignIn = ServiceBuilder.authService.postSignIn(
                    RequestSignIn(
                        requireNotNull(email.value),
                        requireNotNull(password.value)
                    )
                )
                SharedPreferenceController.setJwtToken(
                    responseSignIn.result.jwt
                )
                setSuccessSignIn(true)
            } catch (e: HttpException) {
                Log.d("로그인",e.message().toString())
                Log.d("로그인",e.code().toString())
            }
        }
    }

    fun setSuccessSignIn(isSuccess: Boolean) {
        _isSuccessSignIn.value = isSuccess
    }
}