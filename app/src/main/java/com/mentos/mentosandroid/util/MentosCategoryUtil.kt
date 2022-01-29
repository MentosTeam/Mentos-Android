package com.mentos.mentosandroid.util

import android.widget.TextView
import com.mentos.mentosandroid.R

fun TextView.setMentosText(category: Int) {
    when (category) {
        1 -> this.setText(R.string.bottom_sheet_mentos_red)
        2 -> this.setText(R.string.bottom_sheet_mentos_orange)
        3 -> this.setText(R.string.bottom_sheet_mentos_yellow)
        4 -> this.setText(R.string.bottom_sheet_mentos_green)
        5 -> this.setText(R.string.bottom_sheet_mentos_green_dark)
        6 -> this.setText(R.string.bottom_sheet_mentos_sky)
        7 -> this.setText(R.string.bottom_sheet_mentos_blue)
        8 -> this.setText(R.string.bottom_sheet_mentos_pink)
        9 -> this.setText(R.string.bottom_sheet_mentos_purple)
        10 -> this.setText(R.string.bottom_sheet_mentos_brown_light)
        11 -> this.setText(R.string.bottom_sheet_mentos_brown_red)
        12 -> this.setText(R.string.bottom_sheet_mentos_gray)
    }
}