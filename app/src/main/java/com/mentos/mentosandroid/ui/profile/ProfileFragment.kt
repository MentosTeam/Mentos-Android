package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileBinding
import com.mentos.mentosandroid.ui.home.BOTH
import com.mentos.mentosandroid.ui.home.ONLY_MENTEE
import com.mentos.mentosandroid.ui.home.ONLY_MENTOR
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        initLayout()

        val profileViewPager = binding.profileTabVp
        intiViewPager(profileViewPager)

        val tabLayout = binding.profileTab
        initTab(tabLayout, profileViewPager)

        //버튼 클릭리스너
        setBtnWriteClickListener()

        //탭 선택 시 화면 변경
        setNowState(profileViewPager, tabLayout)

        return binding.root
    }

    private fun initTab(tabLayout: TabLayout, profileViewPager: ViewPager2) {
        val tabTitle = arrayOf("멘토 프로필", "멘티 프로필")
        TabLayoutMediator(tabLayout, profileViewPager) { tab, position ->
            val profileState = 3
            when (profileState) {
                ONLY_MENTOR -> {
                    if(position == 0){
                        tab.view.isClickable = true
                    }else if(position == 1){
                        tab.view.isClickable = false
                    }
                    tab.view.background = getDrawable(requireContext(),  R.drawable.selector_profile_only_tab)
                }
                ONLY_MENTEE -> {
                    if(position == 0){
                        tab.view.isClickable = false
                    }else if(position == 1){
                        tab.view.isClickable = true
                    }
                    tab.view.background = getDrawable(requireContext(),  R.drawable.selector_profile_only_tab)
                }
                BOTH -> {
                    if(position == 0){
                        tab.view.isClickable = true
                    }else if(position == 1){
                        tab.view.isClickable = true
                    }
                    tab.view.background = getDrawable(requireContext(),  R.drawable.selector_profile_both_tab)
                }
            }
            tab.text = tabTitle[position]
        }.attach()
    }

    private fun intiViewPager(profileViewPager: ViewPager2) {
        val profileViewPagerAdapter = ProfileViewPagerAdapter(this)
        //스와이프 막기
        profileViewPager.isUserInputEnabled = false
        profileViewPager.adapter = profileViewPagerAdapter
    }

    private fun setNowState(
        profileViewPager: ViewPager2,
        tabLayout: TabLayout
    ) {
        profileViewPager.currentItem = SharedPreferenceController.getNowState(requireContext())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                SharedPreferenceController.setNowState(requireContext(), tab?.position)
                initLayout()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun setBtnWriteClickListener() {
        binding.profileWriteIb.setOnClickListener {
            navigate(R.id.action_profileFragment_to_searchCreateFragment)
        }
    }

    private fun initLayout() {
        when (SharedPreferenceController.getNowState(requireContext())) {
            //멘토
            0 -> {
                binding.profileWriteIb.visibility = View.VISIBLE
            }
            //멘티
            1 -> {
                binding.profileWriteIb.visibility = View.GONE
            }
        }
    }
}