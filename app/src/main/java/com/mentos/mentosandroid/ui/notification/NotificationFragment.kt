package com.mentos.mentosandroid.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentNotificationBinding
import com.mentos.mentosandroid.util.popBackStack


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    lateinit var notificationViewModel: NotificationViewModel
    private val args by navArgs<NotificationFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        initViewModel()
        initLayout()
        setBtnBackClickListener()

        return binding.root
    }

    private fun initLayout() {
        when (args.from) {
            "setting" -> {
                binding.notificationTitleTv.setText(R.string.setting_notice)
                notificationViewModel.getNotification()
            }
            "home" -> {
                binding.notificationTitleTv.setText(R.string.notification_title)
                notificationViewModel.getPush()
            }
        }
    }

    private fun initViewModel() {
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        binding.notificationViewModel = notificationViewModel
        binding.lifecycleOwner = this
    }

    private fun setBtnBackClickListener() {
        binding.notificationBackIb.setOnClickListener {
            popBackStack()
        }
    }
}