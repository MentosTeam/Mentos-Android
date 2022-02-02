package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.databinding.FragmentMentoringStart2Binding
import com.mentos.mentosandroid.util.SharedPreferenceController
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart2Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart2Binding
    private val args by navArgs<MentoringStart2FragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart2Binding.inflate(inflater, container, false)

        setBtnBackClickListener()
        setEditTextChangeListener()
        setBtnStartClickListener()

        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart2BackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setBtnStartClickListener() {
        binding.mentoringStart2ButtonTv.setOnClickListener {
            val mentosCount = binding.mentoringStart2NumberBlankEt.text.toString()
            if (mentosCount.equals("")) {
                binding.mentoringStart2ErrorTv.visibility = View.VISIBLE
            } else {
                val mentoringStart = args.mentoringStart
                mentoringStart.mentos = Integer.parseInt(mentosCount)
                navigateWithData(
                    MentoringStart2FragmentDirections.actionMentoringStart2Fragment2ToMentoringStart3Fragment2(
                        mentoringStart
                    )
                )
            }
        }
    }

    private fun setEditTextChangeListener() {
        binding.mentoringStart2NumberBlankEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val mentosCount = binding.mentoringStart2NumberBlankEt.text.toString()
                chkTextValidation(mentosCount)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun chkTextValidation(mentosCount: String) {
        if (mentosCount.equals("") || Integer.parseInt(mentosCount) == 0) {
            binding.mentoringStart2ButtonTv.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.mentoringStart2ButtonTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_c7c6))
            binding.mentoringStart2ButtonTv.isClickable = false
            binding.mentoringStart2ErrorTv.visibility = View.VISIBLE
            binding.mentoringStart2Error2Tv.visibility = View.GONE
        } else if (Integer.parseInt(mentosCount) > SharedPreferenceController.getMyMentos(requireContext())) {
            binding.mentoringStart2ButtonTv.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.mentoringStart2ButtonTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_c7c6))
            binding.mentoringStart2ButtonTv.isClickable = false
            binding.mentoringStart2ErrorTv.visibility = View.GONE
            binding.mentoringStart2Error2Tv.visibility = View.VISIBLE
        } else {
            binding.mentoringStart2ButtonTv.setBackgroundResource(R.drawable.shape_black_fill_8)
            binding.mentoringStart2ButtonTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.mentoringStart2ButtonTv.isClickable = true
            binding.mentoringStart2ErrorTv.visibility = View.GONE
            binding.mentoringStart2Error2Tv.visibility = View.GONE
        }
    }
}