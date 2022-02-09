package com.mentos.mentosandroid.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.data.response.OtherMentor
import com.mentos.mentosandroid.data.response.MentorPost
import com.mentos.mentosandroid.data.response.MenteeCategory
import com.mentos.mentosandroid.data.response.MentorCategory

object HomeBindingAdapter {
    //@BindingAdapter으로 함수를 작성하면 커스텀한 속성을 xml에서 사용 가능하다
    //@JvmStatic을 작성하여 스태틱으로 생성
    @BindingAdapter("menteeItems")
    @JvmStatic
    fun setMenteeItems(recyclerView: RecyclerView, items: ArrayList<Mentee>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MenteeRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val menteeAdapter = recyclerView.adapter as MenteeRVAdapter

        menteeAdapter.menteeList = items
        menteeAdapter.notifyDataSetChanged()
    }

    //멘티 홈 - 다른 영역 멘토
    @BindingAdapter("otherMentorItems")
    @JvmStatic
    fun setOtherMentorItems(recyclerView: RecyclerView, items: ArrayList<OtherMentor>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = OtherMentorRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val otherMentorAdapter = recyclerView.adapter as OtherMentorRVAdapter

        otherMentorAdapter.otherMentorList = items
        otherMentorAdapter.notifyDataSetChanged()
    }

    //멘티 홈 - 멘토 글RV
    @BindingAdapter("mentorPostItems")
    @JvmStatic
    fun setMentorPostItems(recyclerView: RecyclerView, items: ArrayList<MentorPost>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MentorPostRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val mentorAdapter = recyclerView.adapter as MentorPostRVAdapter

        mentorAdapter.mentorList = items
        mentorAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("menteeCategoryItems")
    @JvmStatic
    fun setMenteeCategoryItems(recyclerView: RecyclerView, items: ArrayList<MenteeCategory>?) {
        if (items == null)
            return
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

    //멘티 홈 - 멘토 카테고리RV
    @BindingAdapter("mentorCategoryItems")
    @JvmStatic
    fun setMentorCategoryItems(recyclerView: RecyclerView, items: ArrayList<MentorCategory>?) {
        if (items == null)
            return
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