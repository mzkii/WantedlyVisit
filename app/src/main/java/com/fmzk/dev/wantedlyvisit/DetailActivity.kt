package com.fmzk.dev.wantedlyvisit



import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotterknife.bindView

import com.google.gson.Gson




class DetailActivity : AppCompatActivity() {
    private val companyUrl: TextView by bindView(R.id.company_url)
    private val author: TextView by bindView(R.id.author)
    private val description: TextView by bindView(R.id.detail_description)
    private val company: TextView by bindView(R.id.detail_company)
    private val lookingFor: TextView by bindView(R.id.description_looking_for)
    private val backdrop: ImageView by bindView(R.id.backdrop)
    private val companyIcon: ImageView by bindView(R.id.company_icon)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val data = Gson().fromJson<JobDetailHolder>(
                intent.getStringExtra("json_data"),
                JobDetailHolder::class.java!!)
        author.text = data.jobDetail.title
        description.text = data.jobDetail.description
        lookingFor.text = data.jobDetail.looking_for
        company.text = data.jobDetail.company.name
        companyUrl.text = data.jobDetail.company.url

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
    }
}
