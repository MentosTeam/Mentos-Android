package com.mentos.mentosandroid.data

import com.mentos.mentosandroid.data.Mentor

data class MentorCategory(
    var img: String,
    var category: String,
    var mentorList: ArrayList<Mentor>
)
