package com.mentos.mentosandroid.util

import android.content.Context
import android.widget.Toast

fun makeToast(context: Context, msg: Int) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        .show()
}
