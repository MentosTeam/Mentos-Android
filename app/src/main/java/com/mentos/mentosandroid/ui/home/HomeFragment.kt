package com.mentos.mentosandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentHomeBinding
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import timber.log.Timber

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        initLayout()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.w("getInstanceId failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            if (token != null) {
                if (SharedPreferenceController.getDeviceFcmToken() == "" || SharedPreferenceController.getDeviceFcmToken() == token) {
                    homeViewModel.postDeviceFcmToken(token, token)
                    SharedPreferenceController.setDeviceFcmToken(token)
                } else if (SharedPreferenceController.getDeviceFcmToken() != token) {
                    homeViewModel.postDeviceFcmToken(
                        SharedPreferenceController.getDeviceFcmToken(),
                        token
                    )
                    SharedPreferenceController.setDeviceFcmToken(token)
                }
            }
        })

        return binding.root
    }

    private fun initLayout() {
        val transaction = childFragmentManager.beginTransaction()
        when (SharedPreferenceController.getNowState()) {
            0 -> {
                val mentorHomeFragment = MentorHomeFragment()
                transaction.replace(R.id.home_fragment_container, mentorHomeFragment).commit()
            }
            1 -> {
                val menteeHomeFragment = MenteeHomeFragment()
                transaction.replace(R.id.home_fragment_container, menteeHomeFragment).commit()
            }
        }
    }


}