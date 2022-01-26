package com.mentos.mentosandroid.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mentos.mentosandroid.databinding.DialogSearchDetailBinding

class SearchDetailDialog : BottomSheetDialogFragment() {
    lateinit var binding: DialogSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSearchDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
}