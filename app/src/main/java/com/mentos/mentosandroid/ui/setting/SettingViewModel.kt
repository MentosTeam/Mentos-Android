package com.mentos.mentosandroid.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import java.util.regex.Pattern

class SettingViewModel : ViewModel() {
    //닉네임 관련
    val newNickName = MutableLiveData("")

//    private val _savedNickName = MutableLiveData("")
//    var savedNickName: LiveData<String> = _savedNickName

    private val _isNickNameValid = MutableLiveData(false)
    val isNickNameValid: LiveData<Boolean> = _isNickNameValid

    private val _isSuccessNickName = MutableLiveData<Boolean>()
    val isSuccessNickName: LiveData<Boolean> = _isSuccessNickName

    fun getNickNameValid() {
        // 닉네임 중복 서버 api 연결
        setNickNameValid(true)
        // _savedNickName.value = nowNickName.value!!
    }

    fun setNickNameValid(valid: Boolean) {
        _isNickNameValid.value = valid
    }

    private val _canNickNameRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(newNickName, isNickNameValid)
    ) { canNickNameRegisterCheck() }
    val canNickNameRegister: LiveData<Boolean> = _canNickNameRegister

    private fun canNickNameRegisterCheck() =
        requireNotNull(newNickName.value).isNotBlank()
                && requireNotNull(isNickNameValid.value)

    fun setPostNickName() {
        setSuccessNickName(true)
    }

    fun setSuccessNickName(isSuccess: Boolean) {
        _isSuccessNickName.value = isSuccess
    }

    //전공, 학번 관련
    val major = MutableLiveData("")
    val year = MutableLiveData("")

    private val _isSuccessMajor = MutableLiveData<Boolean>()
    val isSuccessMajor: LiveData<Boolean> = _isSuccessMajor

    private val _canMajorRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(major, year)
    ) { canMajorRegisterCheck() }
    val canMajorRegister: LiveData<Boolean> = _canMajorRegister

    private fun canMajorRegisterCheck() =
        requireNotNull(major.value).isNotBlank()
                && requireNotNull(year.value).isNotBlank()
                && requireNotNull(year.value).length == 2

    fun setPostMajor() {
        setSuccessMajor(true)
    }

    fun setSuccessMajor(isSuccess: Boolean) {
        _isSuccessMajor.value = isSuccess
    }


    //성별 공개 설정


    //프로필 사진 설정


    //비밀번호 변경 설정
    var currentPassword = ""
    val nowPassword = MutableLiveData("")

    private val _isNowPasswordCorrect = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(nowPassword)
    ) { isSameCurrentPasswordCheck() }
    val isNowPasswordCorrect: LiveData<Boolean> = _isNowPasswordCorrect

    private fun isSameCurrentPasswordCheck() =
        requireNotNull(nowPassword.value).isBlank() ||
                currentPassword == nowPassword.value

    val newPassword = MutableLiveData("")
    val newPasswordCheck = MutableLiveData("")

    private val _isSamePassword = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(newPassword, newPasswordCheck)
    ) { isSamePasswordCheck() }
    val isSamePassword: LiveData<Boolean> = _isSamePassword

    private val _isSamePasswordFinal = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(newPassword, newPasswordCheck)
    ) { isSamePasswordFinalCheck() }
    val isSamePasswordFinal: LiveData<Boolean> = _isSamePasswordFinal

    private val _newPasswordValid = Transformations.map(newPassword) { newPassword ->
        Pattern.matches(
            "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[@$!%*#?&]).{8,20}+$",
            newPassword
        ) || newPassword.isBlank()
    }
    val newPasswordValid: LiveData<Boolean> = _newPasswordValid

    private val _canPasswordRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            nowPassword,
            newPassword,
            newPasswordCheck,
            isNowPasswordCorrect,
            isSamePassword,
            newPasswordValid
        )
    ) { canPasswordRegisterCheck() }
    val canPasswordRegister: LiveData<Boolean> = _canPasswordRegister

    private fun isSamePasswordCheck() =
        requireNotNull(newPassword.value).isBlank() ||
                requireNotNull(newPasswordCheck.value).isBlank() ||
                newPassword.value == newPasswordCheck.value

    private fun isSamePasswordFinalCheck() =
        requireNotNull(newPassword.value).isNotBlank()
                && requireNotNull(newPasswordCheck.value).isNotBlank()
                && newPassword.value == newPasswordCheck.value

    private fun canPasswordRegisterCheck() =
        requireNotNull(nowPassword.value).isNotBlank()
                && requireNotNull(newPassword.value).isNotBlank()
                && requireNotNull(newPasswordCheck.value).isNotBlank()
                && isNowPasswordCorrect.value == true
                && isSamePassword.value == true
                && newPasswordValid.value == true

    //최종 비밀번호 변경
    private val _isSuccessChangePW = MutableLiveData<Boolean>()
    val isSuccessChangePW: LiveData<Boolean> = _isSuccessChangePW

    fun setPostChangePW() {
        setSuccessChangePW(true)
    }

    fun setSuccessChangePW(isSuccess: Boolean) {
        _isSuccessChangePW.value = isSuccess
    }


    //멘토스, 자기소개 변경 설정

}