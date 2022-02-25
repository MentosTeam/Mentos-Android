package com.mentos.mentosandroid.ui.signup

import androidx.lifecycle.*
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestSchoolCheck
import com.mentos.mentosandroid.data.request.RequestSignUp
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Integer.parseInt
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    val name = MutableLiveData("")
    val nowNickName = MutableLiveData("")

    private val _sex = MutableLiveData("")
    val sex: LiveData<String> = _sex

    private val _setLoading = MutableLiveData<Boolean>()
    val setLoading: LiveData<Boolean> = _setLoading

    private val _isNameValid = Transformations.map(name) { name ->
        Pattern.matches(
            "^(?=.*[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]).{1,10}+$",
            name
        ) || name.isBlank()
    }
    val isNameValid: LiveData<Boolean> = _isNameValid

    private val _isNickNameValid = Transformations.map(nowNickName) { nickname ->
        Pattern.matches(
            "^(?=.*[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]).{1,10}+$",
            nickname
        )
    }
    val isNickNameValid: LiveData<Boolean> = _isNickNameValid

    private val _isNickNameCheck = MutableLiveData(false)
    val isNickNameCheck: LiveData<Boolean> = _isNickNameCheck

    private val _canFirstRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(name, nowNickName, isNickNameCheck, sex, isNameValid)
    ) { canFirstRegisterCheck() }
    val canFirstRegister: LiveData<Boolean> = _canFirstRegister

    private fun canFirstRegisterCheck() =
        requireNotNull(name.value).isNotBlank()
                && requireNotNull(nowNickName.value).isNotBlank()
                && requireNotNull(isNickNameCheck.value)
                && requireNotNull(sex.value).isNotBlank()
                && isNameValid.value == true

    fun getNickNameValid() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.getNickNameCheck(
                    requireNotNull(nowNickName.value)
                )
                when (response.code) {
                    3402 -> setNickNameValid(true)
                    else -> setNickNameValid(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun setNickNameValid(valid: Boolean) {
        _isNickNameCheck.value = valid
    }

    fun setSex(sex: String) {
        _sex.value = sex
    }

    fun setLoadingState(isLoading: Boolean) {
        _setLoading.value = isLoading
    }


    // signUpSecond
    val school = MutableLiveData("")
    val email = MutableLiveData("")
    val emailConfirm = MutableLiveData("")
    val isEmailDuplicate = MutableLiveData(false)
    var emailConfirmNumber = ""

    private val _isDomainValid = MutableLiveData<Boolean>()
    val isDomainValid: LiveData<Boolean> = _isDomainValid

    private val _isEmailConfirmValid = MutableLiveData<Boolean>()
    val isEmailConfirmValid: LiveData<Boolean> = _isEmailConfirmValid

    private val _canSecondRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(school, email, emailConfirm, isDomainValid)
    ) { canSecondRegisterCheck() }
    val canSecondRegister: LiveData<Boolean> = _canSecondRegister

    private fun canSecondRegisterCheck() =
        if (isDomainValid.value != null) {
            requireNotNull(school.value).isNotBlank()
                    && requireNotNull(email.value).isNotBlank()
                    && requireNotNull(emailConfirm.value).isNotBlank()
                    && isDomainValid.value!!
        } else {
            requireNotNull(school.value).isNotBlank()
                    && requireNotNull(email.value).isNotBlank()
                    && requireNotNull(emailConfirm.value).isNotBlank()
        }

    fun postEmailConfirm() {
        setLoadingState(true)
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.postSchoolCheck(
                    RequestSchoolCheck(
                        requireNotNull(school.value),
                        requireNotNull(email.value)
                    )
                )
                setLoadingState(false)
                when (response.code) {
                    1000 -> {
                        setDomainValid(true)
                        emailConfirmNumber = response.result.certificationNumber
                    }
                    2021 -> {
                        setDomainValid(false)
                        isEmailDuplicate.value = true
                    }
                    else -> setDomainValid(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun setDomainValid(valid: Boolean) {
        _isDomainValid.value = valid
    }

    fun setEmailConfirm(confirm: Boolean) {
        _isEmailConfirmValid.value = confirm
    }


    // signUpThird
    val major = MutableLiveData("")
    val year = MutableLiveData("")

    private val _canThirdRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(major, year)
    ) { canThirdRegisterCheck() }
    val canThirdRegister: LiveData<Boolean> = _canThirdRegister

    private fun canThirdRegisterCheck() =
        requireNotNull(major.value).isNotBlank()
                && requireNotNull(year.value).isNotBlank()
                && requireNotNull(year.value).length == 2


    // signUpFourth
    val password = MutableLiveData("")
    val passwordCheck = MutableLiveData("")
    private val isTermsServiceChecked = MutableLiveData(false)
    private val isTermsPersonalChecked = MutableLiveData(false)

    private val _isSuccessSignUp = MutableLiveData<Boolean>()
    val isSuccessSignUp: LiveData<Boolean> = _isSuccessSignUp

    private val _isSamePassword = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(password, passwordCheck)
    ) { isSamePasswordCheck() }
    val isSamePassword: LiveData<Boolean> = _isSamePassword

    private val _isSamePasswordFinal = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(password, passwordCheck)
    ) { isSamePasswordFinalCheck() }
    val isSamePasswordFinal: LiveData<Boolean> = _isSamePasswordFinal

    private val _passwordValid = Transformations.map(password) { password ->
        Pattern.matches(
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&]).{8,20}+$",
            password
        ) || password.isBlank()
    }
    val passwordValid: LiveData<Boolean> = _passwordValid

    private val _canFourthRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(password, passwordCheck, isTermsServiceChecked, isTermsPersonalChecked, isSamePassword, passwordValid)
    ) { canFourthRegisterCheck() }
    val canFourthRegister: LiveData<Boolean> = _canFourthRegister

    private fun isSamePasswordCheck() =
        requireNotNull(password.value).isBlank() ||
                requireNotNull(passwordCheck.value).isBlank() ||
                password.value == passwordCheck.value

    private fun isSamePasswordFinalCheck() =
        requireNotNull(password.value).isNotBlank()
                && requireNotNull(passwordCheck.value).isNotBlank()
                && password.value == passwordCheck.value

    private fun canFourthRegisterCheck() =
        requireNotNull(password.value).isNotBlank()
                && requireNotNull(passwordCheck.value).isNotBlank()
                && isTermsServiceChecked.value == true
                && isTermsPersonalChecked.value == true
                && isSamePassword.value == true
                && passwordValid.value == true

    fun setTermsServiceCheck() {
        isTermsServiceChecked.value = !requireNotNull(isTermsServiceChecked.value)
    }

    fun setTermsPersonalCheck() {
        isTermsPersonalChecked.value = !requireNotNull(isTermsPersonalChecked.value)
    }

    fun setPostSignUp() {
        viewModelScope.launch {
            try {
                val responseSignUp = ServiceBuilder.authService.postSignUp(
                    RequestSignUp(
                        memberEmail = requireNotNull(email.value),
                        memberName = requireNotNull(name.value),
                        memberNickName = requireNotNull(nowNickName.value),
                        memberPw = requireNotNull(password.value),
                        memberSchoolName = requireNotNull(school.value),
                        memberSex = requireNotNull(sex.value),
                        memberStudentId = parseInt(year.value!!),
                        memberMajor = requireNotNull(major.value)
                    )
                )

                when (responseSignUp.isSuccess) {
                    true -> {
                        setSuccessSignUp(true)
                        with(SharedPreferenceController) {
                            setJwtToken(responseSignUp.result.memberJwt)
                            setMemberId(responseSignUp.result.memberId)
                            setAutoLogin(
                                email.value.toString(),
                                password.value.toString()
                            )
                        }
                    }
                    false -> setSuccessSignUp(false)
                }
            } catch (e: HttpException) {
            }
        }
    }

    fun setSuccessSignUp(isSuccess: Boolean) {
        _isSuccessSignUp.value = isSuccess
    }
}