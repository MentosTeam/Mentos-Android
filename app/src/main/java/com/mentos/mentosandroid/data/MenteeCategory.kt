package com.mentos.mentosandroid.data

import com.mentos.mentosandroid.data.Profile

data class MenteeCategory(
    var img: String,
    var category: String,
    var menteeList: ArrayList<Profile>
)
