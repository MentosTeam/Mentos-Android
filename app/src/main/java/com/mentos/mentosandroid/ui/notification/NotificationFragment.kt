package com.mentos.mentosandroid.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mentos.mentosandroid.databinding.FragmentNotificationBinding
import com.mentos.mentosandroid.ui.home.HomeViewModel
import com.mentos.mentosandroid.ui.search.SearchMentorAdapter
import com.mentos.mentosandroid.util.popBackStack


class NotificationFragment : Fragment() {
    private lateinit var binding: FragmentNotificationBinding
    lateinit var notificationViewModel: NotificationViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationBinding.inflate(inflater, container, false)

        initViewModel()
        setBtnBackClickListener()

        return binding.root
    }

    private fun initViewModel() {
        //뷰모델 연결
        notificationViewModel = ViewModelProvider(this).get(NotificationViewModel::class.java)
        binding.notificationViewModel = notificationViewModel

        //뷰모델을 LifeCycle에 종속시킴, LifeCycle 동안 옵저버 역할을 함
        binding.lifecycleOwner = this
    }

    private fun setBtnBackClickListener() {
        binding.notificationBackIb.setOnClickListener {
            popBackStack()
        }
    }
}