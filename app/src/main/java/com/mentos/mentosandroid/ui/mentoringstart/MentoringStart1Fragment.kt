package com.mentos.mentosandroid.ui.mentoringstart

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mentos.mentosandroid.R
import com.mentos.mentosandroid.data.MentoringStart
import com.mentos.mentosandroid.databinding.FragmentMentoringStart1Binding
import com.mentos.mentosandroid.util.navigate
import com.mentos.mentosandroid.util.navigateWithData
import com.mentos.mentosandroid.util.popBackStack

class MentoringStart1Fragment : Fragment() {
    private lateinit var binding: FragmentMentoringStart1Binding
    private val args by navArgs<MentoringStart1FragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMentoringStart1Binding.inflate(inflater, container, false)

        setBtnBackClickListener()
        setEditTextChangeListener()
        setBtnStartClickListener()

        return binding.root
    }

    private fun setBtnBackClickListener() {
        binding.mentoringStart1BackIb.setOnClickListener {
            popBackStack()
        }
    }

    private fun setBtnStartClickListener() {
        binding.mentoringStart1ButtonTv.setOnClickListener {
            val mentoringCount = binding.mentoringStart1NumberBlankEt.text.toString()
            if(mentoringCount.equals("")){
                binding.mentoringStart1ErrorTv.visibility = View.VISIBLE
            }else{
                navigateWithData(
                    MentoringStart1FragmentDirections.actionMentoringStart1FragmentToMentoringStart2Fragment22(
                        MentoringStart(null, args.mentorId, Integer.parseInt(mentoringCount), null)
                    )
                )
            }
        }
    }

    private fun setEditTextChangeListener() {
        binding.mentoringStart1NumberBlankEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val mentoringCount = binding.mentoringStart1NumberBlankEt.text.toString()
                chkTextValidation(mentoringCount)
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    private fun chkTextValidation(mentoringCount: String) {
        if (mentoringCount.equals("") || Integer.parseInt(mentoringCount) == 0) {
            binding.mentoringStart1ButtonTv.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.mentoringStart1ButtonTv.isClickable = false
            binding.mentoringStart1ErrorTv.visibility = View.VISIBLE
        } else if (Integer.parseInt(mentoringCount) in 1..10) {
            binding.mentoringStart1ButtonTv.setBackgroundResource(R.drawable.shape_black_fill_8)
            binding.mentoringStart1ButtonTv.isClickable = true
            binding.mentoringStart1ErrorTv.visibility = View.GONE
        } else {
            binding.mentoringStart1ButtonTv.setBackgroundResource(R.drawable.shape_gray_fill_8)
            binding.mentoringStart1ButtonTv.isClickable = false
            binding.mentoringStart1ErrorTv.visibility = View.VISIBLE
        }
    }
}