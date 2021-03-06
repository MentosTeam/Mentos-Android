package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringAcceptBinding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg41
import com.mentos.mentosandroid.util.makeToast
import com.mentos.mentosandroid.util.popBackStack

class MentoringAcceptFragment : Fragment() {
    private lateinit var binding: FragmentMentoringAcceptBinding
    private val args by navArgs<MentoringAcceptFragmentArgs>()
    private val mentoringStartViewModel by activityViewModels<MentoringStartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringAcceptBinding.inflate(inflater, container, false)

        initLayout()
        setBtnBackClickListener()

        binding.mentoringAcceptYesTv.setOnClickListener {
            mentoringStartViewModel.patchMentoringAccept(args.stateWait.mentoringId, true)
            mentoringStartViewModel.isSuccessAccept.observe(viewLifecycleOwner) { isSuccessAccept ->
                if (isSuccessAccept != null) {
                    if (isSuccessAccept) {
                        binding.mentoringAcceptButtonTv.visibility = View.VISIBLE
                        binding.mentoringAcceptYesNoLayout.visibility = View.GONE
                        binding.mentoringAcceptSubtitle1Tv.setText(R.string.mentoring_accepted_subtitle1)
                        binding.mentoringAcceptSubtitle2Tv.setText(R.string.mentoring_accepted_subtitle2)
                    } else {
                        makeToast(requireContext(), R.string.toast_mentoring_accept_fail)
                    }
                }
            }

        }

        binding.mentoringAcceptNoTv.setOnClickListener {
            mentoringStartViewModel.patchMentoringAccept(args.stateWait.mentoringId, false)
            mentoringStartViewModel.isSuccessAccept.observe(viewLifecycleOwner) { isSuccessAccept ->
                if (isSuccessAccept != null) {
                    if (isSuccessAccept) {
                        makeToast(requireContext(), R.string.toast_mentoring_refuse)
                        popBackStack()
                    } else {
                        makeToast(requireContext(), R.string.toast_mentoring_refuse_fail)
                    }
                }
            }
        }

        binding.mentoringAcceptButtonTv.setOnClickListener {
            popBackStack()
        }
        return binding.root
    }

    private fun initLayout() {
        binding.mentoringAcceptButtonTv.visibility = View.GONE

        val stateWait = args.stateWait

        binding.mentoringAcceptLayout.setMentosColor(stateWait.majorCategoryId)
        binding.mentoringAcceptMentosCompleteIv.setMentosImg41(stateWait.majorCategoryId)
        binding.mentoringAcceptMentosCompleteNumberTv.text =
            "????????? ${stateWait.mentoringCount.toString()}???(??????-??? ${stateWait.mentoringMentos}???)"
        binding.mentoringAcceptMentosCompleteNameTv.text =
            "${stateWait.mentoringMentorName} ?????? / ${stateWait.mentoringMenteeName} ??????"
    }

    private fun setBtnBackClickListener() {
        binding.mentoringAcceptBackIb.setOnClickListener {
            popBackStack()
        }
    }
}