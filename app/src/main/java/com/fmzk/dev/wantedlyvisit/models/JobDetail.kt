package com.fmzk.dev.wantedlyvisit.models

import com.google.gson.annotations.SerializedName

/**
 * Created by fmzk on 2017/10/24.
 */
data class JobDetail(
        @SerializedName("page_view") val pageView: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("looking_for") val lookingFor: String,
        @SerializedName("image") val image: Image,
        @SerializedName("company") val company: Company)
