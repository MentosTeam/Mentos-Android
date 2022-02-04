package com.mentos.mentosandroid.ui.account

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentos.mentosandroid.util.MediatorLiveDataUtil

class AccountViewModel : ViewModel() {
    val introduce = MutableLiveData("")

    private val _image = MutableLiveData<Uri?>()
    val image: MutableLiveData<Uri?> = _image

    private val _selectedCategory = MutableLiveData<List<Int>>()
    val selectedCategory: LiveData<List<Int>> = _selectedCategory

    private val tempCategory = mutableListOf<Int>()

    private val _selectedState = MutableLiveData<Int>()
    val selectedState: LiveData<Int> = _selectedState

    private val _canRegister = MediatorLiveDataUtil.initMediatorLiveData(
        listOf(introduce)
    ) { canRegisterCheck() }
    val canRegister: LiveData<Boolean> = _canRegister

    private fun canRegisterCheck() =
        requireNotNull(introduce.value).isNotBlank()
                && introduce.value!!.length > 9

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

    fun setState(state: Int) {
        _selectedState.value = state
    }

    fun setImage(imgUri: Uri) {
        _image.value = imgUri
    }
}