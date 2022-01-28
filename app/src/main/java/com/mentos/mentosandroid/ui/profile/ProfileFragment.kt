package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.mentos.mentosandroid.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private val tabTitle = arrayOf("멘토 프로필", "멘티 프로필")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        val profileViewPagerAdapter = ProfileViewPagerAdapter(this)


        val profileViewPager = binding.profileTabVp
        //스와이프 막기
        profileViewPager.isUserInputEnabled = false
        profileViewPager.adapter = profileViewPagerAdapter


        val tabLayout = binding.profileTab
        TabLayoutMediator(tabLayout, profileViewPager) { tab, position ->
            //특정 탭 클릭 못하게
//            if(position == 0){
//                tab.view.isClickable = false
//            }
            tab.text = tabTitle[position]
        }.attach()

        //첫 화면 설정
        profileViewPager.setCurrentItem(1)
        return binding.root
    }
}