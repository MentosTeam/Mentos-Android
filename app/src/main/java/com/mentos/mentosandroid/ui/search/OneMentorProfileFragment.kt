package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentOneMentorProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileMentorFragment
import com.mentos.mentosandroid.util.popBackStack

class OneMentorProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMentorProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMentorProfileBinding.inflate(inflater, container, false)
        setBackBtnClickListener()
        childFragmentManager.beginTransaction()
            .replace(binding.oneMentorProfileContainerLayout.id, ProfileMentorFragment())
            .addToBackStack(null)
            .commit()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.oneMentorBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }
}