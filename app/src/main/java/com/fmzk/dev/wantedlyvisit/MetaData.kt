package com.fmzk.dev.wantedlyvisit

import com.google.gson.annotations.SerializedName

/**
 * Created by fmzk on 2017/10/24.
 */
data class MetaData(
        @SerializedName("total_objects")    val TotalObjects:   Int,
        @SerializedName("per_page")         val PerPage:        Int,
        @SerializedName("total_pages")      val TotalPages:     Int)
