package com.mentos.mentosandroid.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.MenteeCategory
import com.mentos.mentosandroid.data.Mentor
import com.mentos.mentosandroid.data.MentorCategory
import com.mentos.mentosandroid.data.Profile

object HomeBindingAdapter {
    //@BindingAdapter으로 함수를 작성하면 커스텀한 속성을 xml에서 사용 가능하다
    //@JvmStatic을 작성하여 스태틱으로 생성
    @BindingAdapter("profileItems")
    @JvmStatic
    fun setProfileItems(recyclerView: RecyclerView, items: ArrayList<Profile>) {
        if (recyclerView.adapter == null) {
            val adapter = ProfileRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val profileAdapter = recyclerView.adapter as ProfileRVAdapter

        profileAdapter.profileList = items
        profileAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentorItems")
    @JvmStatic
    fun setMentorItems(recyclerView: RecyclerView, items: ArrayList<Mentor>) {
        if (recyclerView.adapter == null) {
            val adapter = MentorRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val mentorAdapter = recyclerView.adapter as MentorRVAdapter

        mentorAdapter.mentorList = items
        mentorAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("menteeCategoryItems")
    @JvmStatic
    fun setMenteeCategoryItems(recyclerView: RecyclerView, items: ArrayList<MenteeCategory>) {
        if (recyclerView.adapter == null) {
            val adapter = MenteeCategoryRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val menteeCategoryRVAdapter = recyclerView.adapter as MenteeCategoryRVAdapter

        menteeCategoryRVAdapter.menteeCategoryList = items
        menteeCategoryRVAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentorCategoryItems")
    @JvmStatic
    fun setMentorCategoryItems(recyclerView: RecyclerView, items: ArrayList<MentorCategory>) {
        if (recyclerView.adapter == null) {
            val adapter = MentorCategoryRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val mentorCategoryRVAdapter = recyclerView.adapter as MentorCategoryRVAdapter

        mentorCategoryRVAdapter.mentorCategoryList = items
        mentorCategoryRVAdapter.notifyDataSetChanged()
    }

}