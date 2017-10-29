package com.fmzk.dev.wantedlyvisit

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.jcodecraeer.xrecyclerview.ProgressStyle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotterknife.bindView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import kotlin.collections.ArrayList
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import com.google.gson.Gson
import timber.log.Timber


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var searchView: SearchView
    private val recyclerView: XRecyclerView by bindView(R.id.recycler_view)
    private val activityMain: RelativeLayout by bindView(R.id.activity_main)
    private val client = WantedlyVisitClient()
    private var adapter: ItemListAdapter? = null
    private var listData: ArrayList<JobDetail>? = null
    private var currentPage = 1
    private var totalPages = 1
    private var keyword = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "シゴトを探す"

        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate)
        recyclerView.setLoadingListener(
                object : XRecyclerView.LoadingListener {
                    override fun onRefresh() {
                        getJobs(isClearList = true)
                    }
                    override fun onLoadMore() {
                        getJobs(isClearList = false)
                    }
                }
        )
        listData = ArrayList()
        adapter = ItemListAdapter(listData!!) { item ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            val data = JobDetailHolder(item)
            val json = Gson().toJson(data)
            intent.putExtra("json_data", json)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.refresh()
    }

    fun errorSnackbar() {
        recyclerView.refreshComplete()
        adapter?.notifyDataSetChanged()
        Snackbar.make(activityMain, "通信に失敗しました", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
    }

    fun getJobs(isClearList: Boolean){
        when (isClearList) {
            true -> {
                currentPage = 1
            }
            false -> {
                currentPage++
                if (currentPage > totalPages) {
                    recyclerView.loadMoreComplete()
                    adapter?.notifyDataSetChanged()
                    return
                }
            }
        }
        client.getJobs(keyword, currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if(isClearList) listData?.clear()
                            totalPages = it.metaData.TotalPages
                            listData?.addAll(it.data)
                            recyclerView.refreshComplete()
                            adapter?.notifyDataSetChanged()
                        },
                        { Timber.e(it, "getJobs");errorSnackbar() }
                )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.activity_search, menu)
        val menuItem = menu.findItem(R.id.toolbar_menu_search)
        menuItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem): Boolean {
                return true
            }
            override fun onMenuItemActionCollapse(item: MenuItem): Boolean {
                onBackPressed()
                return false
            }
        })
        menuItem.expandActionView()

        searchView = menuItem.actionView as SearchView
        searchView.maxWidth = 10000
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                return onQueryTextChange(query)
            }
            override fun onQueryTextChange(newText: String): Boolean {
                keyword = newText
                recyclerView.refreshComplete()
                getJobs(isClearList = true)
                return false
            }
        })
        return true
    }
}
