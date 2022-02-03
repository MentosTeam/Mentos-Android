package com.mentos.mentosandroid.ui.account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentAccountPhotoBinding
import com.mentos.mentosandroid.ui.main.FirstAccountActivity
import com.mentos.mentosandroid.ui.main.MainActivity
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class AccountPhotoFragment : Fragment() {
    private lateinit var binding: FragmentAccountPhotoBinding
    private val accountViewModel by viewModels<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountPhotoBinding.inflate(inflater, container, false)
        setBtnClickListener()
        return binding.root
    }

    private fun setBtnClickListener() {
        binding.accountPhotoBtnComplete.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }

        binding.accountPhotoBtnNextTv.setOnClickListener {
            startActivity(Intent(requireContext(), MainActivity::class.java))
            requireActivity().finish()
        }
    }
}