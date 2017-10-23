package com.fmzk.dev.wantedlyvisit

import com.google.gson.annotations.SerializedName

/**
 * Created by fmzk on 2017/10/24.
 */
data class Jobs(
        @SerializedName("data") val data: List<JobDetail>,
        @SerializedName("_metadata") val metadata: MetaData)
