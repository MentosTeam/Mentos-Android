package com.mentos.mentosandroid.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentAccountDialogBinding
import com.mentos.mentosandroid.util.navigateWithData

class AccountDialogFragment : Fragment() {
    private lateinit var binding: FragmentAccountDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountDialogBinding.inflate(layoutInflater, container, false)
        setClickListener()
        return binding.root
    }

    private fun setClickListener() {
        binding.dialogAccountBtnMentee.setOnClickListener {
            navigateWithData(
                AccountDialogFragmentDirections.actionFirstAccountDialogToAccountMentosFragment(
                    2
                )
            )
        }

        binding.dialogAccountBtnMentor.setOnClickListener {
            navigateWithData(
                AccountDialogFragmentDirections.actionFirstAccountDialogToAccountMentosFragment(
                    1
                )
            )
        }
    }
}