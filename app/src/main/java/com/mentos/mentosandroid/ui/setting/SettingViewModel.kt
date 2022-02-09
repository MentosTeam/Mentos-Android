package com.mentos.mentosandroid.ui.setting

import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.request.RequestChangeMentos
import com.mentos.mentosandroid.data.request.RequestChangePW
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import com.mentos.mentosandroid.util.SharedPreferenceController
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.regex.Pattern

class SettingViewModel : ViewModel() {

    fun getMentorData() {
        Log.d("설정 멘토", SharedPreferenceController.getJwtToken())
        viewModelScope.launch {
            try {
                val responseSetting =
                    //멘토
                    if (SharedPreferenceController.getNowState() == 0) {
                        ServiceBuilder.settingService.getSettingMentor()
                    } else {
                        ServiceBuilder.settingService.getSettingMentee()
                    }

                Log.d("설정 데이터", responseSetting.message)
                newNickName.value = responseSetting.result.memberNickName
                major.value = responseSetting.result.memberMajor
                _image.value =
                    if (responseSetting.result.memberImage == null) {
                        null
                    } else {
                        Uri.parse(responseSetting.result.memberImage)
                    }
                introduce.value = responseSetting.result.memberIntro
                tempCategory = mutableListOf()
                tempCategory.add(responseSetting.result.memberMajorFirst)
                if (responseSetting.result.memberMajorSecond != 0) {
                    tempCategory.add(responseSetting.result.memberMajorSecond)
                }
            } catch (e: HttpException) {
                Log.d("설정 데이터", e.message().toString())
                Log.d("설정 데이터", e.code().toString())
            }
        }
    }

    //닉네임 관련
    val newNickName = MutableLiveData("")

    private val _isNickNameValid = MutableLiveData(false)
    val isNickNameValid: LiveData<Boolean> = _isNickNameValid

    private var _isSuccessNickName = MutableLiveData<Boolean>()
    var isSuccessNickName: LiveData<Boolean> = _isSuccessNickName

    fun getNickNameValid() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.authService.getNickNameCheck(
                    requireNotNull(newNickName.value)
                )
                Log.d("중복 확인", response.message)
                if (response.isSuccess) {
                    setNickNameValid(true)
                } else {
                    setNickNameValid(false)
                }
            } catch (e: HttpException) {
            }
        }
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

    fun postNickName() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.settingService.postNickName(
                    requireNotNull(newNickName.value)
                )
                Log.d("닉네임 변경", response.message)
                setSuccessNickName(true)
            } catch (e: HttpException) {
                setSuccessNickName(false)
            }
        }
    }

    private fun setSuccessNickName(isSuccess: Boolean) {
        _isSuccessNickName.value = isSuccess
    }

    fun initSuccessNickName() {
        _isSuccessNickName = MutableLiveData<Boolean>()
        isSuccessNickName = _isSuccessNickName
    }

    //전공 관련
    val major = MutableLiveData("")

    private var _isSuccessMajor = MutableLiveData<Boolean>()
    var isSuccessMajor: LiveData<Boolean> = _isSuccessMajor

    private val _canMajorRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(major)
    ) { canMajorRegisterCheck() }
    val canMajorRegister: LiveData<Boolean> = _canMajorRegister

    private fun canMajorRegisterCheck() =
        requireNotNull(major.value).isNotBlank()

    fun postMajor() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.settingService.postMajor(
                    requireNotNull(major.value)
                )
                Log.d("전공 변경", response.message)
                setSuccessMajor(true)
            } catch (e: HttpException) {
                setSuccessMajor(false)
            }
        }
    }

    private fun setSuccessMajor(isSuccess: Boolean) {
        _isSuccessMajor.value = isSuccess
    }

    fun initSuccessMajor() {
        _isSuccessMajor = MutableLiveData<Boolean>()
        isSuccessMajor = _isSuccessMajor
    }

    //성별 공개 설정
    val openSex = MutableLiveData<Boolean>()


    //프로필 사진 설정
//    private val currentImage: Uri? = //현재 사진
//        if (memberData.value?.memberImage == null) {
//            null
//        } else {
//            Uri.parse(memberData.value?.memberImage)
//        }
    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    private val _newImage = MutableLiveData<Uri?>()
    val newImage: MutableLiveData<Uri?> = _newImage

    fun setImage(imgUri: Uri) {
        _newImage.value = imgUri
    }

    private val _canImageRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(image)
    ) { true }
    val canImageRegister: LiveData<Boolean> = _canImageRegister

//    private fun canMajorImageCheck() =
//        currentImage != image.value

    private val _isSuccessImage = MutableLiveData<Boolean>()
    val isSuccessImage: LiveData<Boolean> = _isSuccessImage

    fun setPostImage() {
        setSuccessImage(true)
    }

    fun setSuccessImage(isSuccess: Boolean) {
        _isSuccessImage.value = isSuccess
    }


    //비밀번호 변경 설정
    private var currentPassword = SharedPreferenceController.getUserPw()
    val nowPassword = MutableLiveData("")

    private val _isNowPasswordCorrect = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(nowPassword)
    ) { isSameCurrentPasswordCheck() }
    val isNowPasswordCorrect: LiveData<Boolean> = _isNowPasswordCorrect

    private fun isSameCurrentPasswordCheck() =
        requireNotNull(nowPassword.value).isBlank()
                || currentPassword == nowPassword.value

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

    private val _isDiffCurrentNew = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(newPassword)
    ) { isDiffCurrentNewCheck() }
    val isDiffCurrentNew: LiveData<Boolean> = _isDiffCurrentNew

    private fun isDiffCurrentNewCheck() =
        requireNotNull(newPassword.value).isBlank()
                || currentPassword != newPassword.value

    private val _canPasswordRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(
            nowPassword,
            newPassword,
            newPasswordCheck,
            isNowPasswordCorrect,
            isSamePassword,
            newPasswordValid,
            isDiffCurrentNew
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
                && isDiffCurrentNew.value == true

    //최종 비밀번호 변경
    private var _isSuccessChangePW = MutableLiveData<Boolean>()
    var isSuccessChangePW: LiveData<Boolean> = _isSuccessChangePW

    fun postChangePW() {
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.settingService.postPW(
                    RequestChangePW(
                        currentPassword,
                        requireNotNull(newPassword.value)
                    )
                )
                Log.d("비번 변경", response.message)
                setSuccessChangePW(true)
            } catch (e: HttpException) {
                setSuccessChangePW(false)
            }
        }
    }

    private fun setSuccessChangePW(isSuccess: Boolean) {
        _isSuccessChangePW.value = isSuccess
    }

    fun initSuccesChangePW() {
        _isSuccessChangePW = MutableLiveData<Boolean>()
        isSuccessChangePW = _isSuccessChangePW
    }


    //멘토스, 자기소개 변경 설정
    val introduce = MutableLiveData("")
    var tempCategory = mutableListOf<Int>()

    private var _selectedCategory = MutableLiveData<List<Int>>()
    var selectedCategory: LiveData<List<Int>> = _selectedCategory

    private val _canIntroRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(introduce)
    ) { canIntroRegisterCheck() }
    val canIntroRegister: LiveData<Boolean> = _canIntroRegister

    private fun canIntroRegisterCheck() =
        requireNotNull(introduce.value).isNotBlank()
                && introduce.value!!.length > 9

    private val _canMentosRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(selectedCategory)
    ) { canMentosRegisterCheck() }
    val canMentosRegister: LiveData<Boolean> = _canMentosRegister

    private fun canMentosRegisterCheck() =
        selectedCategory.value!!.isNotEmpty()

    fun setCategory(category: Int) {
        tempCategory.add(category)
        _selectedCategory.value = tempCategory
    }

    fun removeCategory(category: Int) {
        tempCategory.remove(category)
        _selectedCategory.value = tempCategory
    }

    fun clearCategory() {
        tempCategory.clear()
        _selectedCategory.value = tempCategory
    }

    fun setTempCategory() {
        _selectedCategory.value = tempCategory
    }

    private var _isSuccessMentosIntro = MutableLiveData<Boolean>()
    var isSuccessMentosIntro: LiveData<Boolean> = _isSuccessMentosIntro

    fun postMentosIntro() {
        val requestChangeMentos =
            if (selectedCategory.value!!.size == 2) {
                RequestChangeMentos(
                    requireNotNull(introduce.value),
                    selectedCategory.value!![0],
                    selectedCategory.value!![1],
                    SharedPreferenceController.getNowState() + 1
                )
            } else {
                RequestChangeMentos(
                    kotlin.requireNotNull(introduce.value),
                    selectedCategory.value!![0],
                    0,
                    SharedPreferenceController.getNowState() + 1
                )
            }
        viewModelScope.launch {
            try {
                val response = ServiceBuilder.settingService.postMentosIntro(
                    requestChangeMentos
                )
                Log.d("멘토스, 소개 변경", response.message)
                setSuccessMentosIntro(true)
            } catch (e: HttpException) {
                setSuccessMentosIntro(false)
            }
        }
    }

    private fun setSuccessMentosIntro(isSuccess: Boolean) {
        _isSuccessMentosIntro.value = isSuccess
    }

    fun initSuccessMentosIntro() {
        _isSuccessMentosIntro = MutableLiveData<Boolean>()
        isSuccessMentosIntro = _isSuccessMentosIntro
    }
}