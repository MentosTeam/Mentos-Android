package com.mentos.mentosandroid.ui.myprofiledetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mentos.mentosandroid.data.api.ServiceBuilder
import com.mentos.mentosandroid.data.response.SearchMentor
import kotlinx.coroutines.launch
import retrofit2.HttpException

class PostListViewModel : ViewModel() {

    private val _myPostList = MutableLiveData<ArrayList<SearchMentor>>()
    val myPostList: LiveData<ArrayList<SearchMentor>> = _myPostList

    fun getMyPostList() {
        viewModelScope.launch {
            try {
                val responseMyPostList = ServiceBuilder.profileService.getMyPostList()
                Log.d("내가 쓴 글", responseMyPostList.message)
                _myPostList.value = responseMyPostList.result
            }catch (e: HttpException){
                Log.d("내가 쓴 글", e.code().toString())
                Log.d("내가 쓴 글", e.message().toString())
            }
        }
    }
}