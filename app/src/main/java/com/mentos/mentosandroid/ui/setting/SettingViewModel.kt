package com.mentos.mentosandroid.ui.setting

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.data.Setting
import com.mentos.mentosandroid.util.MediatorLiveDataUtil
import java.util.regex.Pattern

class SettingViewModel : ViewModel() {

    val memberData = Setting(
        "와죠스키",
        1,
        "데이터사이언스",
        1, 2,
        null,
        "기존 자기소개 입니다 입니다 입니다"
    )


    //닉네임 관련
    private val currentNickName = memberData.memberNickName
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
                && currentNickName != newNickName.value

    fun setPostNickName() {
        setSuccessNickName(true)
    }

    fun setSuccessNickName(isSuccess: Boolean) {
        _isSuccessNickName.value = isSuccess
    }

    //전공 관련
    private val currentMajor = memberData.memberMajor
    val major = MutableLiveData("")

    private val _isSuccessMajor = MutableLiveData<Boolean>()
    val isSuccessMajor: LiveData<Boolean> = _isSuccessMajor

    private val _canMajorRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(major)
    ) { canMajorRegisterCheck() }
    val canMajorRegister: LiveData<Boolean> = _canMajorRegister

    private fun canMajorRegisterCheck() =
        requireNotNull(major.value).isNotBlank()
                && currentMajor != major.value

    fun setPostMajor() {
        setSuccessMajor(true)
    }

    fun setSuccessMajor(isSuccess: Boolean) {
        _isSuccessMajor.value = isSuccess
    }


    //성별 공개 설정
    val openSex = MutableLiveData<Boolean>()


    //프로필 사진 설정
    private val currentImage: Uri? = //현재 사진
        if (memberData.memberImage == null) {
            null
        } else {
            Uri.parse(memberData.memberImage)
        }
    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    fun setImage(imgUri: Uri) {
        _image.value = imgUri
    }

    private val _canImageRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(image)
    ) { canMajorImageCheck() }
    val canImageRegister: LiveData<Boolean> = _canImageRegister

    private fun canMajorImageCheck() =
        currentImage != image.value

    private val _isSuccessImage = MutableLiveData<Boolean>()
    val isSuccessImage: LiveData<Boolean> = _isSuccessImage

    fun setPostImage() {
        setSuccessImage(true)
    }

    fun setSuccessImage(isSuccess: Boolean) {
        _isSuccessImage.value = isSuccess
    }


    //비밀번호 변경 설정
    private var currentPassword = "mentos123!"
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

    private val _isDiffCurrentNew = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(newPassword)
    ) { isDiffCurrentNewCheck() }
    val isDiffCurrentNew: LiveData<Boolean> = _isDiffCurrentNew

    private fun isDiffCurrentNewCheck() =
        requireNotNull(newPassword.value).isBlank() ||
                currentPassword != newPassword.value

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
    private val _isSuccessChangePW = MutableLiveData<Boolean>()
    val isSuccessChangePW: LiveData<Boolean> = _isSuccessChangePW

    fun setPostChangePW() {
        setSuccessChangePW(true)
    }

    fun setSuccessChangePW(isSuccess: Boolean) {
        _isSuccessChangePW.value = isSuccess
    }


    //멘토스, 자기소개 변경 설정
    private val currentIntroduce = memberData.memberIntro
    val introduce = MutableLiveData(currentIntroduce)


    var currentMentos =
        if (memberData.memberMajorSecond == 0) {
            listOf(memberData.memberMajorFirst)
        } else {
            listOf(memberData.memberMajorFirst, memberData.memberMajorSecond)
        }
    private var tempCategory = currentMentos.toMutableList()

    private val _selectedCategory = MutableLiveData<List<Int>>()
    val selectedCategory: LiveData<List<Int>> = _selectedCategory


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
        _selectedCategory.value = tempCategory.toMutableList()
    }

    fun removeCategory(category: Int) {
        tempCategory.remove(category)
        _selectedCategory.value = tempCategory.toMutableList()
    }

    fun clearCategory() {
        tempCategory.clear()
        _selectedCategory.value = tempCategory.toMutableList()
    }

    fun setTempCategory() {
        _selectedCategory.value = tempCategory.toMutableList()
    }

    private val _isSuccessMentosIntro = MutableLiveData<Boolean>()
    val isSuccessMentosIntro: LiveData<Boolean> = _isSuccessMentosIntro

    fun setPostMentosIntro() {
        setSuccessMentosIntro(true)
    }

    fun setSuccessMentosIntro(isSuccess: Boolean) {
        _isSuccessMentosIntro.value = isSuccess
    }


    //로그아웃
    fun logout() {

    }

    //회원탈퇴

    fun withdraw() {

    }
}