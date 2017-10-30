package com.fmzk.dev.wantedlyvisit

/**
 * Created by fmzk on 2017/10/28.
 */
import com.fmzk.dev.wantedlyvisit.models.JobDetail
import java.io.Serializable

data class JobDetailHolder(
        var jobDetail: JobDetail) : Serializable
