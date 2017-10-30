package com.fmzk.dev.wantedlyvisit.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.fmzk.dev.wantedlyvisit.R
import com.fmzk.dev.wantedlyvisit.databinding.ActivityDetailBinding
import com.fmzk.dev.wantedlyvisit.models.JobDetail
import com.google.gson.Gson


class DetailActivity : AppCompatActivity() {
    lateinit private var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val jobDetail = Gson().fromJson<JobDetail>(
                intent.getStringExtra("json_data"),
                JobDetail::class.java!!)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding!!.detailActivity = this
        binding.jobDetail = jobDetail
        binding.imageUrl = jobDetail.image.i_320_131_x2
        binding.companyIconUrl = jobDetail.company.avatar.original
        binding.liked.setOnClickListener { view ->
            showMessage(view, "「応援する」が押されました")
        }
        binding.fabShare.setOnClickListener { view ->
            showMessage(view, "「SNS共有」が押されました")
        }
        binding.buttonBookmark.setOnClickListener { view ->
            showMessage(view, "「あとで見る」に登録されました")
        }
        binding.buttonCandidate.setOnClickListener { view ->
            showMessage(view, "話を聞きにいきたい」が押されました")
        }
    }

    private fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
    }
}
