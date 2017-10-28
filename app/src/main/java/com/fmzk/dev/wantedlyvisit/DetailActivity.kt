package com.fmzk.dev.wantedlyvisit


import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotterknife.bindView

import com.google.gson.Gson


class DetailActivity : AppCompatActivity() {

    private val founder: TextView by bindView(R.id.detail_founder)
    private val foundedOn: TextView by bindView(R.id.detail_founded_on)
    private val payrollNumber: TextView by bindView(R.id.detail_payroll_number)
    private val address: TextView by bindView(R.id.detail_address)
    private val url: TextView by bindView(R.id.detail_url)
    private val pageView: TextView by bindView(R.id.page_view)

    private val author: TextView by bindView(R.id.author)
    private val description: TextView by bindView(R.id.detail_description)
    private val company: TextView by bindView(R.id.detail_company)
    private val lookingFor: TextView by bindView(R.id.description_looking_for)
    private val backdrop: ImageView by bindView(R.id.backdrop)
    private val companyIcon: ImageView by bindView(R.id.company_icon)
    private val bookmark: Button by bindView(R.id.button_bookmark)
    private val candidate: Button by bindView(R.id.button_candidate)
    private val share: FloatingActionButton by bindView(R.id.fab_share)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data = Gson().fromJson<JobDetailHolder>(
                intent.getStringExtra("json_data"),
                JobDetailHolder::class.java!!)

        pageView.text = data.jobDetail.page_view + " " + "views"
        founder.text = data.jobDetail.company.founder
        foundedOn.text = data.jobDetail.company.founded_on
        payrollNumber.text = data.jobDetail.company.payroll_number
        address.text = data.jobDetail.company.address_prefix + " " +
                data.jobDetail.company.address_suffix
        url.text = data.jobDetail.company.url

        author.text = data.jobDetail.title
        description.text = data.jobDetail.description
        lookingFor.text = data.jobDetail.looking_for
        company.text = data.jobDetail.company.name

        if (data.jobDetail.image != null) {
            Glide.with(backdrop.context)
                    .load(data.jobDetail.image.i_320_131_x2)
                    .error(android.R.drawable.ic_delete)
                    .into(backdrop)
        }

        if (data.jobDetail.company.avatar != null) {
            Glide.with(companyIcon.context)
                    .load(data.jobDetail.company.avatar.original)
                    .error(android.R.drawable.ic_delete)
                    .into(companyIcon)
        }

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
    }
}
