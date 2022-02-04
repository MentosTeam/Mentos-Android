package com.mentos.mentosandroid.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    // signUpFirst
    val name = MutableLiveData("")
    val nowNickName = MutableLiveData("")

    private val _sex = MutableLiveData("")
    val sex: LiveData<String> = _sex

//    private val _savedNickName = MutableLiveData("")
//    var savedNickName: LiveData<String> = _savedNickName

    private val _isNickNameValid = MutableLiveData(false)
    val isNickNameValid: LiveData<Boolean> = _isNickNameValid

    private val _canFirstRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(name, nowNickName, isNickNameValid, sex)
    ) { canFirstRegisterCheck() }
    val canFirstRegister: LiveData<Boolean> = _canFirstRegister

    private fun canFirstRegisterCheck() =
        requireNotNull(name.value).isNotBlank()
                && requireNotNull(nowNickName.value).isNotBlank()
                && requireNotNull(isNickNameValid.value)
                && requireNotNull(sex.value).isNotBlank()

    fun getNickNameValid() {
        // 닉네임 중복 서버 api 연결
        setNickNameValid(true)
        // _savedNickName.value = nowNickName.value!!
    }

    fun setNickNameValid(valid: Boolean) {
        _isNickNameValid.value = valid
    }

    fun setSex(sex: String) {
        _sex.value = sex
    }


    // signUpSecond
    val school = MutableLiveData("")
    val email = MutableLiveData("")
    val emailConfirm = MutableLiveData("")

    private val _isDomainValid = MutableLiveData(false)
    val isDomainValid: LiveData<Boolean> = _isDomainValid

    private val _isEmailConfirmValid = MutableLiveData<Boolean>()
    val isEmailConfirmValid: LiveData<Boolean> = _isEmailConfirmValid

    private val _canSecondRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(school, email, emailConfirm, isDomainValid)
    ) { canSecondRegisterCheck() }
    val canSecondRegister: LiveData<Boolean> = _canSecondRegister

    private fun canSecondRegisterCheck() =
        requireNotNull(school.value).isNotBlank()
                && requireNotNull(email.value).isNotBlank()
                && requireNotNull(emailConfirm.value).isNotBlank()
                && requireNotNull(isDomainValid.value)

    fun postEmailConfirm() {
        // 학교 인증 api
        // 도메인 ( 리턴값이 참일 경루 -> setDomainValid(ture) 아니면 false -> > Fragment에서 다이얼로그
        setDomainValid(true)

        // 인증번호를 받아오는 변수, emailConfirm이랑 비교 -> 일치 시 setEmailConfirm(true) 아니면 false -> Fragment에서 다이얼로그
        setEmailConfirm(true)
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
    private val isTermsChecked = MutableLiveData(false)

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
        listOf(password, passwordCheck, isTermsChecked, isSamePassword, passwordValid)
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
                && isTermsChecked.value == true
                && isSamePassword.value == true
                && passwordValid.value == true

    fun setTermsCheck() {
        isTermsChecked.value = !requireNotNull(isTermsChecked.value)
    }

    fun setPostSignUp() {
        setSuccessSignUp(true)
    }

    fun setSuccessSignUp(isSuccess: Boolean) {
        _isSuccessSignUp.value = isSuccess
    }
}