package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.data.response.SearchMentor
import com.mentos.mentosandroid.databinding.FragmentMentorPostListBinding
import com.mentos.mentosandroid.util.popBackStack

class MentorPostListFragment : Fragment() {
    lateinit var binding: FragmentMentorPostListBinding

    private val args by navArgs<MentorPostListFragmentArgs>()
    private lateinit var mentorPostList: MutableLiveData<List<SearchMentor>>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentorPostListBinding.inflate(inflater, container, false)
        mentorPostList = MutableLiveData(args.mentorPostList.toList())
        setBtnBackClickListener()
        setSearchListAdapter()
        setSearchMentorObserver()
        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.mentorPostBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setSearchListAdapter() {
        binding.mentorPostRv.adapter = MentorPostListAdapter()
    }

    private fun setSearchMentorObserver() {
        mentorPostList.observe(viewLifecycleOwner) { list ->
            list?.let {
                with(binding.mentorPostRv.adapter as MentorPostListAdapter) { submitList(list) }
            }
        }
    }
}