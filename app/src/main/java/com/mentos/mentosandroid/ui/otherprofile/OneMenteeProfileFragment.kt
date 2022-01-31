package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.databinding.FragmentOneMenteeProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileViewModel
import com.mentos.mentosandroid.util.popBackStack

class OneMenteeProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMenteeProfileBinding
    lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMenteeProfileBinding.inflate(inflater, container, false)
        initViewModel()
        setBackBtnClickListener()
        return binding.root
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun setBackBtnClickListener() {
        binding.oneMenteeBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }
}

