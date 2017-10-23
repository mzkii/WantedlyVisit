package com.fmzk.dev.wantedlyvisit

import com.google.gson.annotations.SerializedName

/**
 * Created by fmzk on 2017/10/24.
 */

data class Company(
        @SerializedName("name")             val name:           String,
        @SerializedName("address_prefix")   val address_prefix: String,
        @SerializedName("address_suffix")   val address_suffix: String,
        @SerializedName("avatar")           val avatar:         Avatar)
