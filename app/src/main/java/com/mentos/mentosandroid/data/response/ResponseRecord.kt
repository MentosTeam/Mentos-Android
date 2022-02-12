package com.mentos.mentosandroid.data.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ResponseRecord(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: RecordResult
)

data class RecordResult(
    val reports: List<StateRecord>
)

@Parcelize
data class StateRecord(
    val createAt: String,
    val idx: Int,
    val report: String
): Parcelable