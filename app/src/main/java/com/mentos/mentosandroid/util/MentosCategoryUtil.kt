package com.mentos.mentosandroid.util

import android.content.Context
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mentos.mentosandroid.R

object MentosCategoryUtil {
    @BindingAdapter("mentosCategory")
    @JvmStatic
    fun TextView.setMentosText(category: Int) {
        when (category) {
            1 -> this.setText(R.string.mentos_red)
            2 -> this.setText(R.string.mentos_orange)
            3 -> this.setText(R.string.mentos_yellow)
            4 -> this.setText(R.string.mentos_green)
            5 -> this.setText(R.string.mentos_green_dark)
            6 -> this.setText(R.string.mentos_sky)
            7 -> this.setText(R.string.mentos_blue)
            8 -> this.setText(R.string.mentos_pink)
            9 -> this.setText(R.string.mentos_purple)
            10 -> this.setText(R.string.mentos_brown_light)
            11 -> this.setText(R.string.mentos_brown_red)
            12 -> this.setText(R.string.mentos_gray)
        }
    }

    fun geMentosText(category: Int): String {
        return when (category) {
            1 -> "경제/경영"
            2 -> "인문"
            3 -> "사회"
            4 -> "어문"
            5 -> "의학"
            6 -> "공학"
            7 -> "자연과학"
            8 -> "IT"
            9 -> "교육"
            10 -> "건축"
            11 -> "예체능"
            else -> "기타"
        }
    }
}
