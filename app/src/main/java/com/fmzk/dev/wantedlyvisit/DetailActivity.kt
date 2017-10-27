package com.fmzk.dev.wantedlyvisit

/**
 * Created by fmzk on 2017/10/24.
 */
import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import kotterknife.bindView

class DetailActivity : Activity() {
    private val jobTitle: TextView by bindView(R.id.job_title)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        this.title = "DetailActivity"
        this.jobTitle.text = intent.getStringExtra("itemName")
    }
}
