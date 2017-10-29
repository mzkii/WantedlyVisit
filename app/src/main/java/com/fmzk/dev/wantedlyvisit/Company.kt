package com.fmzk.dev.wantedlyvisit

import com.google.gson.annotations.SerializedName

/**
 * Created by fmzk on 2017/10/24.
 */

data class Company(
        @SerializedName("payroll_number") val payrollNumber: String,
        @SerializedName("founded_on") val foundedOn: String,
        @SerializedName("founder") val founder: String,
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String,
        @SerializedName("address_prefix") val addressPrefix: String,
        @SerializedName("address_suffix") val addressSuffix: String,
        @SerializedName("avatar") val avatar: Avatar)
