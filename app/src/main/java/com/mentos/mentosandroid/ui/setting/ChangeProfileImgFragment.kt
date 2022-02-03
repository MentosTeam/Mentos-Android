package com.mentos.mentosandroid.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mentos.mentosandroid.databinding.FragmentChangeProfileImgBinding
import com.mentos.mentosandroid.util.popBackStack

class ChangeProfileImgFragment : Fragment() {
    private lateinit var binding: FragmentChangeProfileImgBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChangeProfileImgBinding.inflate(inflater, container, false)

        setBtnBackClickListener()
        return binding.root

    }

    private fun setBtnBackClickListener() {
        binding.changeImgBackIb.setOnClickListener {
            popBackStack()
        }
    }
}