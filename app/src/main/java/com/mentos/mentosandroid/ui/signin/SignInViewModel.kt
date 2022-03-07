package com.mentos.mentosandroid.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestSignIn
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class SignInViewModel : ViewModel() {
    val email = MutableLiveData(SharedPreferenceController.getUserEmail())
    val password = MutableLiveData(SharedPreferenceController.getUserPw())

    private val _isSuccessSignIn = MutableLiveData<Boolean>()
    val isSuccessSignIn: LiveData<Boolean> = _isSuccessSignIn

    private val _setLoading = MutableLiveData<Boolean>()
    val setLoading: LiveData<Boolean> = _setLoading

    private val _isEmptyProfile = MutableLiveData<Boolean>()
    val isEmptyProfile: LiveData<Boolean> = _isEmptyProfile

    private val _canSignIn = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(email, password)
    ) { canSignInCheck() }
    val canSignIn: LiveData<Boolean> = _canSignIn

    private val _canAutoSignIn = MutableLiveData(canSignInCheck())
    val canAutoSignIn: LiveData<Boolean> = _canAutoSignIn

    private fun canSignInCheck() =
        requireNotNull(email.value).isNotBlank()
                && requireNotNull(password.value).isNotBlank()

    fun postSignIn() {
        setLoadingState(true)
        viewModelScope.launch {
            try {
                val responseSignIn = ServiceBuilder.authService.postSignIn(
                    RequestSignIn(
                        requireNotNull(email.value.toString()),
                        requireNotNull(password.value.toString())
                    )
                )
                setLoadingState(false)
                when (responseSignIn.code) {
                    1000 -> {
                        with(SharedPreferenceController) {
                            setJwtToken(responseSignIn.result.jwt)
                            setMemberId(responseSignIn.result.memberId)
                            setAutoLogin(
                                email.value.toString(),
                                password.value.toString()
                            )
                            setOpenSex(responseSignIn.result.genderFlag)
                            setAgreementPush(responseSignIn.result.mentorNotificationFlag)
                        }
                        if (responseSignIn.result.mentor == 1) {
                            if (SharedPreferenceController.getNowState() == -1) {
                                SharedPreferenceController.setNowState(0)
                            }
                            setSuccessSignIn(true)
                            setIsEmptyProfile(false)
                        } else if (responseSignIn.result.mentee == 1) {
                            SharedPreferenceController.setNowState(1)
                            setSuccessSignIn(true)
                            setIsEmptyProfile(false)
                        } else if (responseSignIn.result.mentor == 0 && responseSignIn.result.mentee == 0) {
                            setIsEmptyProfile(true)
                        }
                    }
                    else -> setSuccessSignIn(false)
                }
            } catch (e: HttpException) {
                Timber.d(e.message().toString())
            }
        }
    }

    fun setSuccessSignIn(isSuccess: Boolean) {
        _isSuccessSignIn.value = isSuccess
    }

    fun setLoadingState(isLoading: Boolean) {
        _setLoading.value = isLoading
    }

    fun setIsEmptyProfile(isEmpty: Boolean) {
        _isEmptyProfile.value = isEmpty
    }
}