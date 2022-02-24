package com.mentos.mentosandroid.ui.otherprofile

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.MarginPageTransformer
import com.bumptech.glide.Glide
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentOneMentorProfileBinding
import com.mentos.mentosandroid.ui.profile.ProfileMentosVPAdapter
import com.mentos.mentosandroid.data.local.SharedPreferenceController
import com.mentos.mentosandroid.util.customdialog.EditTextDialog
import com.mentos.mentosandroid.util.customdialog.OneButtonDialog
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class OneMentorProfileFragment : Fragment() {
    private lateinit var binding: FragmentOneMentorProfileBinding
    lateinit var profileViewModel: OneProfileViewModel
    private val args by navArgs<OneMentorProfileFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneMentorProfileBinding.inflate(inflater, container, false)
        initViewModel()
        initSex()
        initImg()
        setBackBtnClickListener()
        setNavigateWithMentorId()
        setMentorListObserve()
        setReportBtnClickListener()
        setIsMyProfileObserve()
        return binding.root
    }

    private fun setReportBtnClickListener() {
        binding.oneMentorBtnSiren.setOnClickListener {
            EditTextDialog(2) { reportText ->
                profileViewModel.postReport(2, args.mentorId, reportText)
                profileViewModel.isSuccessReport.observe(viewLifecycleOwner) { isSuccess ->
                    if (isSuccess != null && isSuccess) {
                        OneButtonDialog(5) {

                        }.show(childFragmentManager, "report")
                    }
                }
            }.show(childFragmentManager, "report_text")
        }
    }

    private fun setMentorListObserve() {
        profileViewModel.mentorMentosList.observe(viewLifecycleOwner) { mentorMentosList ->
            if (mentorMentosList != null) {
                initMentosVP(mentorMentosList)
            } else {
                initMentosVP(ArrayList())
            }
        }
    }

    private fun initViewModel() {
        profileViewModel = ViewModelProvider(this).get(OneProfileViewModel::class.java)
        profileViewModel.getMentorProfileData(args.mentorId)
        binding.profileViewModel = profileViewModel
        binding.lifecycleOwner = this
    }

    private fun initMentosVP(mentosList: ArrayList<Int>) {
        val mentosVPAdapter = ProfileMentosVPAdapter(this)
        mentosVPAdapter.mentosList = mentosList

        val mentosVP = binding.mentorProfileMentoringMentosVp

        // 좌/우 노출되는 크기를 크게하려면 offsetPx 증가
        val leftOffsetPx = 27.dpToPx(resources.displayMetrics)
        val rightOffsetPx = 18.dpToPx(resources.displayMetrics)
        mentosVP.setPadding(leftOffsetPx, 0, rightOffsetPx, 0)

        // 페이지간 마진 크게하려면 pageMarginPx 증가
        val pageMarginPx = 1.dpToPx(resources.displayMetrics)
        val marginTransformer = MarginPageTransformer(pageMarginPx)
        mentosVP.setPageTransformer(marginTransformer)

        mentosVP.offscreenPageLimit = 3
        mentosVP.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        mentosVP.adapter = mentosVPAdapter
        mentosVP.currentItem = mentosVPAdapter.itemCount
    }

    fun Int.dpToPx(displayMetrics: DisplayMetrics): Int = (this * displayMetrics.density).toInt()


    private fun initSex() {
        val isOpen = SharedPreferenceController.getOpenSex(requireContext())
        if (isOpen) {
            binding.mentorProfileOpenSexLayout.visibility = View.VISIBLE
            binding.mentorProfilePrivateSexLayout.visibility = View.GONE
        } else {
            binding.mentorProfileOpenSexLayout.visibility = View.GONE
            binding.mentorProfilePrivateSexLayout.visibility = View.VISIBLE
        }
    }

    private fun setBackBtnClickListener() {
        binding.oneMentorBtnBackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setNavigateWithMentorId() {
        binding.mentorProfileDetailMoreIb.setOnClickListener {
            navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToMentorPostListFragment(
                    profileViewModel.mentorProfileData.value!!.postArr.toTypedArray()
                )
            )
        }

        binding.mentorProfileBottomMentoringLayout.setOnClickListener {
            it.navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToMentoringStart1Fragment(
                    args.mentorId
                )
            )
        }

        binding.mentorProfileReviewMoreIb.setOnClickListener {
            navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToReviewListFragment(
                    profileViewModel.mentorProfileData.value!!.reviews.toTypedArray()
                )
            )
        }
        binding.mentorProfileBottomChatLayout.setOnClickListener {
            navigateWithData(
                OneMentorProfileFragmentDirections.actionOneMentorProfileFragmentToChatRoomFragment(
                    memberId = profileViewModel.mentorProfileData.value!!.basicInformation.memberId.toString(),
                    nickname = profileViewModel.mentorProfileData.value!!.basicInformation.nickname,
                    imageUrl = profileViewModel.mentorProfileData.value!!.basicInformation.profileImage
                )
            )
        }
    }

    private fun initImg() {
        profileViewModel.mentorProfileData.observe(viewLifecycleOwner) { mentorProfileData ->
            if (mentorProfileData == null) {
                binding.mentorProfileImg.setImageResource(R.drawable.img_home_user)
            } else {
                if (mentorProfileData.basicInformation.profileImage == null) {
                    binding.mentorProfileImg.setImageResource(R.drawable.img_home_user)
                } else {
                    Glide.with(requireContext())
                        .load(mentorProfileData.basicInformation.profileImage)
                        .into(binding.mentorProfileImg)
                }
            }
        }
    }

    private fun setIsMyProfileObserve() {
        profileViewModel.isMyProfile.observe(viewLifecycleOwner) { isMyProfile ->
            if (isMyProfile) {
                binding.mentorProfileBottomMenuLayout.visibility = View.GONE
                binding.oneMentorBtnSiren.visibility = View.GONE
            }
        }
    }
}