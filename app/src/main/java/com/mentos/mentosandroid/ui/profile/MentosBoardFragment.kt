package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.mentos.mentosandroid.databinding.FragmentMentosBoardBinding

class MentosBoardFragment() : Fragment() {

    companion object {
        fun newInstance(mentosSubList: ArrayList<Int>): MentosBoardFragment {
            val args = Bundle().apply {
                putIntegerArrayList("mentosSubList", mentosSubList)
            }

            val fragment = MentosBoardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: FragmentMentosBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMentosBoardBinding.inflate(inflater, container, false)

        var mentosSubList = requireArguments().getIntegerArrayList("mentosSubList")

        val mentosCount = mentosSubList!!.size
        if (mentosSubList.size < 10) {
            for (i in mentosSubList.size until 10) {
                mentosSubList.add(i, 0)
            }
        }

        binding.mentorProfileMentoringMentosCountTv.text = "${mentosCount}/10"

        val mentosAdapter = ProfileMentosRVAdapter()
        mentosAdapter.mentosList = mentosSubList
        mentosAdapter.notifyDataSetChanged()
        binding.mentorProfileMentoringMentosRv.adapter = mentosAdapter

        return binding.root
    }
}