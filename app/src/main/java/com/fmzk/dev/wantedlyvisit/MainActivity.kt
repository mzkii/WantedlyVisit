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
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val client = WantedlyVisitClient()
    private var adapter: ItemListAdapter? = null
    private val recyclerView: XRecyclerView by bindView(R.id.recycler_view)
    private var currentPage = 1
    private var totalPages = 1
    private var keyword = ""
    private var listData: ArrayList<JobDetail>? = null
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "シゴトを探す"
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallRotate)
        recyclerView.setLoadingListener(
                object : XRecyclerView.LoadingListener {
                    override fun onRefresh() {
                        currentPage = 1
                        client.getJobs(keyword, currentPage)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    listData?.clear()
                                    totalPages = it.metadata.TotalPages
                                    listData?.addAll(it.data)
                                    recyclerView.refreshComplete()
                                    adapter?.notifyDataSetChanged()
                                }
                    }
                    override fun onLoadMore() {
                        currentPage++
                        client.getJobs(keyword, currentPage)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe {
                                    totalPages = it.metadata.TotalPages
                                    listData?.addAll(it.data)
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

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                return onQueryTextChange(query)
            }
            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("onQueryTextChange", "newText = " + newText)
                keyword = newText
                currentPage = 1
                recyclerView.refreshComplete()
                client.getJobs(keyword, currentPage)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            listData?.clear()
                            totalPages = it.metadata.TotalPages
                            listData?.addAll(it.data)
                            adapter?.notifyDataSetChanged()
                        }
                return false
            }
        })
        return true
    }
}
