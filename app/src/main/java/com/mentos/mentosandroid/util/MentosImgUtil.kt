package com.mentos.mentosandroid.util

import android.widget.ImageView
import com.mentos.mentosandroid.R

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