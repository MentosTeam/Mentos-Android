package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.databinding.FragmentMentoringStart4Binding
import com.mentos.mentosandroid.util.MentosCategoryUtil.setMentosColor
import com.mentos.mentosandroid.util.MentosImgUtil.setMentosImg41
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart4Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart4Binding
    private val args by navArgs<MentoringStart4FragmentArgs>()
    private val mentoringStartViewModel by activityViewModels<MentoringStartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart4Binding.inflate(inflater, container, false)

        initLayout()
        setBtnBackClickListener()
        setBtnRequestClickListener()
        return binding.root
    }

    private fun setBtnRequestClickListener() {
        binding.mentoringStart4ButtonTv.setOnClickListener {
            //+서버 전송
            mentoringStartViewModel.postMentoringStart(args.mentoringStart)
            mentoringStartViewModel.isSuccess.observe(viewLifecycleOwner) { isSuccess ->
                if(isSuccess != null){
                    if (isSuccess) {
                        //start5로 이동
                        navigateWithData(
                            MentoringStart4FragmentDirections.actionMentoringStart4FragmentToMentoringStart5Fragment(
                                mentoringStart = args.mentoringStart,
                                stateWait = null
                            )
                        )
                    }else{
                        Toast.makeText(requireContext(), "멘토링 요청을 실패했습니다", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart4BackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun initLayout() {
        val mentoringStart = args.mentoringStart

        mentoringStartViewModel.getNickName(mentoringStart.mentoId!!)
        mentoringStartViewModel.nickName.observe(viewLifecycleOwner) { nickName ->
            if (nickName != null) {
                binding.mentoringStart4MentosCompleteNameTv.text =
                    "${nickName.mentoNickname} 멘토 / ${nickName.mentiNickname} 멘티"

                binding.mentoringStart4Layout.setMentosColor(mentoringStart.majorCategoryId)
                binding.mentoringStart4MentosCompleteIv.setMentosImg41(mentoringStart.majorCategoryId)
                binding.mentoringStart4MentosCompleteNumberTv.text =
                    "멘토링 ${mentoringStart.mentoringCount.toString()}회(멘토-쓰 ${mentoringStart.mentos}개)"
            }
        }
    }
}