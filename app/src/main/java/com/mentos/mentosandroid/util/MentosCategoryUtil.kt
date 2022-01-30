package com.mentos.mentosandroid.util

import android.widget.TextView
import com.mentos.mentosandroid.R

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