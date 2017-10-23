package com.fmzk.dev.wantedlyvisit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val client = WantedlyVisitClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client.getJobs("java",1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    for(visitData in it.data){
                        Log.d("MainActivity","--------------------------------------------------")
                        Log.d("MainActivity","title: " + visitData.title)
                        Log.d("MainActivity","image : " + visitData.image.original)
                        Log.d("MainActivity","description: " + visitData.description)
                        Log.d("MainActivity","looking_for: " + visitData.looking_for)
                        Log.d("MainActivity","company_name: " + visitData.company.name)
                        if(visitData.company.avatar != null){
                            Log.d("MainActivity","company_image: " + visitData.company.avatar.original)
                        }
                    }
                    Log.d("MainActivity","--------------------------------------------------")
                    Log.d("MainActivity","total_objects  : " + it.metadata.PerPage)
                }
    }
}
