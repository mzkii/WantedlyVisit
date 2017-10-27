package com.fmzk.dev.wantedlyvisit

/**
 * Created by fmzk on 2017/10/24.
 */
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotterknife.bindView
import android.text.SpannableString
import com.by_syk.lib.texttag.TextTag.POS_START
import android.R.attr.tag
import android.graphics.Color
import com.by_syk.lib.texttag.TextTag


class ItemListAdapter(private val items: ArrayList<JobDetail>, private val itemClick: (String) -> Unit) :
        RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(view = view, itemClick = itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setUp(items[position])
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    class ViewHolder(view: View, val itemClick: (String) -> Unit) : RecyclerView.ViewHolder(view) {
        private val jobTitle: TextView by bindView(R.id.job_title)
        private val company: TextView by bindView(R.id.company)
        private val lookingFor: TextView by bindView(R.id.looking_for)
        private val jobImage: ImageView by bindView(R.id.job_image)
        private val companyIcon: ImageView by bindView(R.id.company_icon)

        fun setUp(jobDetail: JobDetail) {
            this.jobTitle.text = jobDetail.title
            this.company.text = jobDetail.company.name
            this.lookingFor.text = jobDetail.looking_for

            val tt = TextTag.Builder()
                    .text("")
                    .tag(" " + jobDetail.looking_for + " ")
                    .bgColor(-0xde690d)
                    .color(Color.WHITE)
                    .sizeRatio(1.0f)
                    .pos(TextTag.POS_START)
                    .build()
            val ss = tt.render()

            lookingFor.text = ss

            Glide.with(jobImage.context)
                    .load(jobDetail.image.i_304_124_x2)
                    .error(android.R.drawable.ic_delete)
                    .into(jobImage)

            if(jobDetail.company.avatar != null){
                Glide.with(companyIcon.context)
                        .load(jobDetail.company.avatar.original)
                        .error(android.R.drawable.ic_delete)
                        .into(companyIcon)
            }
            this.itemView.setOnClickListener { itemClick(jobDetail.title) }
            this.itemView.setOnClickListener { itemClick(jobDetail.title) }
        }
    }
}
