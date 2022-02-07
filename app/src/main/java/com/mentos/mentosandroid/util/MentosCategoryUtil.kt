package com.mentos.mentosandroid.util

import android.content.Context
import android.view.View
import android.widget.RadioButton
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

    fun getMentosText(category: Int): String {
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

    fun View.setMentosColor(category: Int?) {
        when (category) {
            1 -> this.setBackgroundResource(R.color.mentos_red)
            2 -> this.setBackgroundResource(R.color.mentos_orange)
            3 -> this.setBackgroundResource(R.color.mentos_yellow)
            4 -> this.setBackgroundResource(R.color.mentos_green)
            5 -> this.setBackgroundResource(R.color.mentos_green_dark)
            6 -> this.setBackgroundResource(R.color.mentos_sky)
            7 -> this.setBackgroundResource(R.color.mentos_blue)
            8 -> this.setBackgroundResource(R.color.mentos_pink)
            9 -> this.setBackgroundResource(R.color.mentos_purple)
            10 -> this.setBackgroundResource(R.color.mentos_brown_light)
            11 -> this.setBackgroundResource(R.color.mentos_brown_red)
            12 -> this.setBackgroundResource(R.color.mentos_gray)
        }
    }

    fun View.setMentosBgStroke(category: Int?) {
        when (category) {
            1 -> this.setBackgroundResource(R.drawable.shape_mentos_red_stroke_20)
            2 -> this.setBackgroundResource(R.drawable.shape_mentos_orange_stroke_20)
            3 -> this.setBackgroundResource(R.drawable.shape_mentos_yellow_stroke_20)
            4 -> this.setBackgroundResource(R.drawable.shape_mentos_green_stroke_20)
            5 -> this.setBackgroundResource(R.drawable.shape_mentos_green_dark_stroke_20)
            6 -> this.setBackgroundResource(R.drawable.shape_mentos_sky_stroke_20)
            7 -> this.setBackgroundResource(R.drawable.shape_mentos_blue_stroke_20)
            8 -> this.setBackgroundResource(R.drawable.shape_mentos_pink_stroke_20)
            9 -> this.setBackgroundResource(R.drawable.shape_mentos_purple_stroke_20)
            10 -> this.setBackgroundResource(R.drawable.shape_mentos_brown_light_stroke_20)
            11 -> this.setBackgroundResource(R.drawable.shape_mentos_brown_red_stroke_20)
            12 -> this.setBackgroundResource(R.drawable.shape_mentos_gray_stroke_20)
        }
    }

    fun RadioButton.setSearchCategoryBg(category: Int?) {
        when (category) {
            1 -> this.setBackgroundResource(R.drawable.selector_search_category_red)
            2 -> this.setBackgroundResource(R.drawable.selector_search_category_orange)
            3 -> this.setBackgroundResource(R.drawable.selector_search_category_yellow)
            4 -> this.setBackgroundResource(R.drawable.selector_search_category_green)
            5 -> this.setBackgroundResource(R.drawable.selector_search_category_green_dark)
            6 -> this.setBackgroundResource(R.drawable.selector_search_category_sky)
            7 -> this.setBackgroundResource(R.drawable.selector_search_category_blue)
            8 -> this.setBackgroundResource(R.drawable.selector_search_category_pink)
            9 -> this.setBackgroundResource(R.drawable.selector_search_category_purple)
            10 -> this.setBackgroundResource(R.drawable.selector_search_category_brown_light)
            11 -> this.setBackgroundResource(R.drawable.selector_search_category_brown_red)
            12 -> this.setBackgroundResource(R.drawable.selector_search_category_gray)
        }
    }

    fun RadioButton.setSearchCategoryTextSize(category: Int?) {
        when (category) {
            1 -> this.textSize = 8F
            2 -> this.textSize = 10F
            3 -> this.textSize = 10F
            4 -> this.textSize = 10F
            5 -> this.textSize = 10F
            6 -> this.textSize = 10F
            7 -> this.textSize = 8F
            8 -> this.textSize = 10F
            9 -> this.textSize = 10F
            10 -> this.textSize = 10F
            11 -> this.textSize = 10F
            12 -> this.textSize = 10F
        }
    }
}
