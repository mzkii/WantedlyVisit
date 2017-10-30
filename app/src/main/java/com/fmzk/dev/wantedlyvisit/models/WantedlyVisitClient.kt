package com.fmzk.dev.wantedlyvisit.models

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by fmzk on 2017/10/24.
 */
class WantedlyVisitClient {
    private val baseURL = "https://www.wantedly.com/api/v1/"

    fun getJobs(keyword: String, page: Int): Observable<Jobs> {
        val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(WantedlyVisitAPI::class.java)
        return api.getJobs(keyword, page)
    }

    interface WantedlyVisitAPI {
        @GET("projects?")
        fun getJobs(
                @Query("q") keyword: String,
                @Query("page") page: Int): Observable<Jobs>
    }
}
