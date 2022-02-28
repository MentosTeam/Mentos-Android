package com.mentos.mentosandroid.ui.home

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.Mentee
import com.mentos.mentosandroid.data.response.OtherMentor
import com.mentos.mentosandroid.data.response.MentorPost
import com.mentos.mentosandroid.data.response.MenteeCategory
import com.mentos.mentosandroid.data.response.MentorCategory

object HomeBindingAdapter {
    @BindingAdapter("menteeItems")
    @JvmStatic
    fun setMenteeItems(recyclerView: RecyclerView, items: ArrayList<Mentee>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MenteeRVAdapter()
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter
        }

        val menteeAdapter = recyclerView.adapter as MenteeRVAdapter

        menteeAdapter.menteeList = items
        menteeAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("otherMentorItems")
    @JvmStatic
    fun setOtherMentorItems(recyclerView: RecyclerView, items: ArrayList<OtherMentor>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = OtherMentorRVAdapter()
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }

        val otherMentorAdapter = recyclerView.adapter as OtherMentorRVAdapter

        otherMentorAdapter.otherMentorList = items
        otherMentorAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentorPostItems")
    @JvmStatic
    fun setMentorPostItems(recyclerView: RecyclerView, items: ArrayList<MentorPost>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MentorPostRVAdapter()
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }

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
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }

        val menteeCategoryRVAdapter = recyclerView.adapter as MenteeCategoryRVAdapter

        menteeCategoryRVAdapter.menteeCategoryList = items
        menteeCategoryRVAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentorCategoryItems")
    @JvmStatic
    fun setMentorCategoryItems(recyclerView: RecyclerView, items: ArrayList<MentorCategory>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MentorCategoryRVAdapter()
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }

        val mentorCategoryRVAdapter = recyclerView.adapter as MentorCategoryRVAdapter

        mentorCategoryRVAdapter.mentorCategoryList = items
        mentorCategoryRVAdapter.notifyDataSetChanged()
    }
}