package com.fmzk.dev.wantedlyvisit



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotterknife.bindView

import com.google.gson.Gson




class DetailActivity : AppCompatActivity() {
    private val author: TextView by bindView(R.id.author)
    private val backdrop: ImageView by bindView(R.id.backdrop)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data = Gson().fromJson<JobDetailHolder>(
                intent.getStringExtra("json_data"),
                JobDetailHolder::class.java!!)
        author.text = data.jobDetail.title

        if (data.jobDetail.image != null) {
            Glide.with(backdrop.context)
                    .load(data.jobDetail.image.i_320_131_x2)
                    .error(android.R.drawable.ic_delete)
                    .into(backdrop)
        }
    }
}
