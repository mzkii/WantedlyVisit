package com.fmzk.dev.wantedlyvisit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jcodecraeer.xrecyclerview.ProgressStyle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotterknife.bindView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val client = WantedlyVisitClient()
    private var adapter: ItemListAdapter? = null
    private val recyclerView: XRecyclerView by bindView(R.id.recycler_view)
    private var page = 1
    private var listData: ArrayList<JobDetail>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate)

        recyclerView.setLoadingListener(
                object : XRecyclerView.LoadingListener {
                    override fun onRefresh() {
                        Log.d("MainActivity", "onRefresh")
                        page = 1
                        client.getJobs("", page)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    listData?.clear()
                                    for (data in it.data) {
                                        listData?.add(data)
                                    }
                                    adapter?.notifyDataSetChanged()
                                    recyclerView.refreshComplete()
                                }
                    }

                    override fun onLoadMore() {
                        Log.d("MainActivity", "onLoadMore")
                        page++
                        client.getJobs("", page)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    for (data in it.data) {
                                        listData?.add(data)
                                    }
                                    recyclerView.loadMoreComplete()
                                    adapter?.notifyDataSetChanged()
                                }
                    }
                }
        )

        listData = ArrayList()
        adapter = ItemListAdapter(listData!!) { itemName ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("itemName", itemName)
            this.startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.refresh()
    }
}
