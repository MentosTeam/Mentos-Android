package com.mentos.mentosandroid.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentProfileBinding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.popBackStack

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

        //버튼 클릭리스너
        setBtnWriteClickListener()
        setBtnBackClickListener()

        //탭 선택 시 화면 변경
        setNowState(profileViewPager, tabLayout)
        return binding.root
    }

    private fun setNowState(
        profileViewPager: ViewPager2,
        tabLayout: TabLayout
    ) {
        profileViewPager.currentItem = SharedPreferenceController.getNowState(requireContext())

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                SharedPreferenceController.setNowState(requireContext(), tab?.position)
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

    private fun setBtnBackClickListener() {
        binding.profileBackIb.setOnClickListener {
            popBackStack()
        }
    }
}