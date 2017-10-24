package com.fmzk.dev.wantedlyvisit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotterknife.bindView

class MainActivity : AppCompatActivity() {

    private val client = WantedlyVisitClient()
    private val recyclerView: RecyclerView by bindView(R.id.recycler_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        client.getJobs("java",1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    val adapter = ItemListAdapter(items = it.data) { itemName ->
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("itemName", itemName)
                        this.startActivity(intent)
                    }
                    this.recyclerView.adapter = adapter
                }

    }
}
