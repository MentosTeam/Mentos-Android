package com.mentos.mentosandroid.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mentos.mentosandroid.R


object MentosImgUtil {
    @BindingAdapter("mentosImg17")
    @JvmStatic
    fun ImageView.setMentosImg17(category: Int) {
        when (category) {
            1 -> this.setImageResource(R.drawable.img_mentos_red_17)
            2 -> this.setImageResource(R.drawable.img_mentos_orange_17)
            3 -> this.setImageResource(R.drawable.img_mentos_yellow_17)
            4 -> this.setImageResource(R.drawable.img_mentos_green_17)
            5 -> this.setImageResource(R.drawable.img_mentos_green_dark_17)
            6 -> this.setImageResource(R.drawable.img_mentos_sky_17)
            7 -> this.setImageResource(R.drawable.img_mentos_blue_17)
            8 -> this.setImageResource(R.drawable.img_mentos_pink_17)
            9 -> this.setImageResource(R.drawable.img_mentos_purple_17)
            10 -> this.setImageResource(R.drawable.img_mentos_brown_light_17)
            11 -> this.setImageResource(R.drawable.img_mentos_brown_red_17)
            12 -> this.setImageResource(R.drawable.img_mentos_gray_17)
            else -> this.setImageResource(R.drawable.img_mentos_none_17)
        }
    }

    @BindingAdapter("mentosImg25")
    @JvmStatic
    fun ImageView.setMentosImg25(category: Int) {
        when (category) {
            1 -> this.setImageResource(R.drawable.img_mentos_red_25)
            2 -> this.setImageResource(R.drawable.img_mentos_orange_25)
            3 -> this.setImageResource(R.drawable.img_mentos_yellow_25)
            4 -> this.setImageResource(R.drawable.img_mentos_green_25)
            5 -> this.setImageResource(R.drawable.img_mentos_green_dark_25)
            6 -> this.setImageResource(R.drawable.img_mentos_sky_25)
            7 -> this.setImageResource(R.drawable.img_mentos_blue_25)
            8 -> this.setImageResource(R.drawable.img_mentos_pink_25)
            9 -> this.setImageResource(R.drawable.img_mentos_purple_25)
            10 -> this.setImageResource(R.drawable.img_mentos_brown_light_25)
            11 -> this.setImageResource(R.drawable.img_mentos_brown_red_25)
            12 -> this.setImageResource(R.drawable.img_mentos_gray_25)
        }
    }

    @BindingAdapter("mentosImg37")
    @JvmStatic
    fun ImageView.setMentosImg37(category: Int) {
        when (category) {
            1 -> this.setImageResource(R.drawable.img_mentos_red_37)
            2 -> this.setImageResource(R.drawable.img_mentos_orange_37)
            3 -> this.setImageResource(R.drawable.img_mentos_yellow_37)
            4 -> this.setImageResource(R.drawable.img_mentos_green_37)
            5 -> this.setImageResource(R.drawable.img_mentos_green_dark_37)
            6 -> this.setImageResource(R.drawable.img_mentos_sky_37)
            7 -> this.setImageResource(R.drawable.img_mentos_blue_37)
            8 -> this.setImageResource(R.drawable.img_mentos_pink_37)
            9 -> this.setImageResource(R.drawable.img_mentos_purple_37)
            10 -> this.setImageResource(R.drawable.img_mentos_brown_light_37)
            11 -> this.setImageResource(R.drawable.img_mentos_brown_red_37)
            12 -> this.setImageResource(R.drawable.img_mentos_gray_37)
            else -> this.setBackgroundResource(R.drawable.shape_gray_stroke_oval)
        }
    }

    @BindingAdapter("mentosImg41")
    @JvmStatic
    fun ImageView.setMentosImg41(category: Int?) {
        when (category) {
            1 -> this.setImageResource(R.drawable.img_mentos_red_41)
            2 -> this.setImageResource(R.drawable.img_mentos_orange_41)
            3 -> this.setImageResource(R.drawable.img_mentos_yellow_41)
            4 -> this.setImageResource(R.drawable.img_mentos_green_41)
            5 -> this.setImageResource(R.drawable.img_mentos_green_dark_41)
            6 -> this.setImageResource(R.drawable.img_mentos_sky_41)
            7 -> this.setImageResource(R.drawable.img_mentos_blue_41)
            8 -> this.setImageResource(R.drawable.img_mentos_pink_41)
            9 -> this.setImageResource(R.drawable.img_mentos_purple_41)
            10 -> this.setImageResource(R.drawable.img_mentos_brown_light_41)
            11 -> this.setImageResource(R.drawable.img_mentos_brown_red_41)
            12 -> this.setImageResource(R.drawable.img_mentos_gray_41)
        }
    }

    @BindingAdapter("mentosImg71")
    @JvmStatic
    fun ImageView.setMentosImg71(category: Int) {
        when (category) {
            1 -> this.setImageResource(R.drawable.img_mentos_red_71)
            2 -> this.setImageResource(R.drawable.img_mentos_orange_71)
            3 -> this.setImageResource(R.drawable.img_mentos_yellow_71)
            4 -> this.setImageResource(R.drawable.img_mentos_green_71)
            5 -> this.setImageResource(R.drawable.img_mentos_green_dark_71)
            6 -> this.setImageResource(R.drawable.img_mentos_sky_71)
            7 -> this.setImageResource(R.drawable.img_mentos_blue_71)
            8 -> this.setImageResource(R.drawable.img_mentos_pink_71)
            9 -> this.setImageResource(R.drawable.img_mentos_purple_71)
            10 -> this.setImageResource(R.drawable.img_mentos_brown_light_71)
            11 -> this.setImageResource(R.drawable.img_mentos_brown_red_71)
            12 -> this.setImageResource(R.drawable.img_mentos_gray_71)
        }
    }
}