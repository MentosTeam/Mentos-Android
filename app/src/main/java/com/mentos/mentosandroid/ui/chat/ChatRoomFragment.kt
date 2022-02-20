package com.mentos.mentosandroid.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mentos.mentosandroid.databinding.FragmentChatRoomBinding
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.popBackStack

class ChatRoomFragment : Fragment() {
    private lateinit var binding: FragmentChatRoomBinding
    private val chatViewModel by viewModels<ChatViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatRoomBinding.inflate(layoutInflater, container, false)
        setBackBtnClickListener()
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setReportBtnClickListener()
        return binding.root
    }

    private fun setBackBtnClickListener() {
        binding.chatRoomBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setReportBtnClickListener(){
        binding.chatRoomBtnReportIb.setOnClickListener {
            EditTextDialog(2) { reportText ->
                val memberId = 0
                chatViewModel.postReport(3, memberId, reportText)
                chatViewModel.isSuccessReport.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess){
                        OneButtonDialog(5) {

                        }.show(childFragmentManager, "report")
                    }
                }
            }.show(childFragmentManager, "report_text")
        }
    }
}