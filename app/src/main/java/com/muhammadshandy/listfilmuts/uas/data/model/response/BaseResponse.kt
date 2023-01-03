package com.muhammadshandy.listfilmuts.uas.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Kelas Generic yang membungkus response movie
 */
data class BaseResponse<T>(

    @SerializedName("results")
    val results: List<T>
)
