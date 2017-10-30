package com.fmzk.dev.wantedlyvisit.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fmzk.dev.wantedlyvisit.R
import com.fmzk.dev.wantedlyvisit.databinding.ActivityDetailBinding
import com.fmzk.dev.wantedlyvisit.models.JobDetail
import kotterknife.bindView
import com.google.gson.Gson
import com.sackcentury.shinebuttonlib.ShineButton

class DetailActivity : AppCompatActivity() {
    lateinit private var binding: ActivityDetailBinding
    private val bookmark: Button by bindView(R.id.button_bookmark)
    private val candidate: Button by bindView(R.id.button_candidate)
    private val share: FloatingActionButton by bindView(R.id.fab_share)
    private val liked: ShineButton by bindView(R.id.liked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val jobDetail = Gson().fromJson<JobDetail>(
                intent.getStringExtra("json_data"),
                JobDetail::class.java!!)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.jobDetail = jobDetail
        binding.imageUrl = jobDetail.image.i_320_131_x2
        binding.companyIconUrl = jobDetail.company.avatar.original

        bookmark.setOnClickListener { view ->
            Snackbar.make(view, "「あとで見る」に登録されました", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
        candidate.setOnClickListener { view ->
            Snackbar.make(view, "「話を聞きにいきたい」が押されました", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
        share.setOnClickListener { view ->
            Snackbar.make(view, "「SNS共有」が押されました", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
        liked.setOnClickListener { view ->
            Snackbar.make(view, "「応援する」が押されました", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .show()
        }
    }
}
