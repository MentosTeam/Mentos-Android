package com.mentos.mentosandroid.ui.profile

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProfileMentosVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var mentosList = ArrayList<Int>()

    override fun getItemCount(): Int {
        if (mentosList.size <= 10)
            return 1
        return ((mentosList.size - 1) / 10) + 1
    }

    override fun createFragment(position: Int): Fragment {
        var mentosSubList: MutableList<Int>
        val startIdx = position * 10
        var endIdx = position * 10 + 10
        if (mentosList.size < endIdx) {
            endIdx = mentosList.size
        }

        mentosSubList = ArrayList<Int>(mentosList.subList(startIdx, endIdx))
        return MentosBoardFragment.newInstance(mentosSubList)
    }

}