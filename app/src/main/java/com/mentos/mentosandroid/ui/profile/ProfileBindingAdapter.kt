package com.mentos.mentosandroid.ui.profile

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mentos.mentosandroid.data.response.Review
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.ui.otherprofile.MentorProfilePostRVAdapter

object ProfileBindingAdapter {
    @BindingAdapter("majorItems")
    @JvmStatic
    fun setMajorItems(recyclerView: RecyclerView, items: ArrayList<Int>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = ProfileMajorRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val majorAdapter = recyclerView.adapter as ProfileMajorRVAdapter

        majorAdapter.majorList = items
        majorAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("majorDetailItems")
    @JvmStatic
    fun setMajorDetailItems(recyclerView: RecyclerView, items: ArrayList<SearchMentor>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = ProfileMajorDetailRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val majorDetailAdapter = recyclerView.adapter as ProfileMajorDetailRVAdapter

        majorDetailAdapter.majorDetailList = items
        majorDetailAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentorProfilePostItems")
    @JvmStatic
    fun setMentorProfilePostItems(recyclerView: RecyclerView, items: ArrayList<SearchMentor>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = MentorProfilePostRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val majorDetailAdapter = recyclerView.adapter as MentorProfilePostRVAdapter

        majorDetailAdapter.majorDetailList = items
        majorDetailAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("reviewItems")
    @JvmStatic
    fun setReviewItems(recyclerView: RecyclerView, items: ArrayList<Review>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = ProfileReviewRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val reviewAdapter = recyclerView.adapter as ProfileReviewRVAdapter

        reviewAdapter.reviewList = items
        reviewAdapter.notifyDataSetChanged()
    }

    @BindingAdapter("mentosItems")
    @JvmStatic
    fun setMentosItems(recyclerView: RecyclerView, items: ArrayList<Int>?) {
        if (items == null)
            return
        if (recyclerView.adapter == null) {
            val adapter = ProfileMentosRVAdapter()
            //깜빡임 방지
            adapter.setHasStableIds(true)

            recyclerView.adapter = adapter

        }
        //어댑터 연결
        val mentosAdapter = recyclerView.adapter as ProfileMentosRVAdapter

        mentosAdapter.mentosList = items
        mentosAdapter.notifyDataSetChanged()
    }
}