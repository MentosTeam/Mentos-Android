package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentOneMenteeProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileMenteeFragment
import com.mentos.mentosandroid.util.popBackStack

class OneMenteeProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMenteeProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMenteeProfileBinding.inflate(inflater, container, false)
        setBackBtnClickListener()
        childFragmentManager.beginTransaction()
            .replace(binding.oneMenteeProfileContainerLayout.id, ProfileMenteeFragment())
            .addToBackStack(null)
            .commit()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.oneMenteeBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }
}

