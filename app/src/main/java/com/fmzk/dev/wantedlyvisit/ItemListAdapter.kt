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
        private val title: TextView by bindView(R.id.title)
        private val company: TextView by bindView(R.id.company)
        private val companyUrl: TextView by bindView(R.id.company_url)
        private val lookingFor: TextView by bindView(R.id.looking_for)
        private val description: TextView by bindView(R.id.description)
        private val jobImage: ImageView by bindView(R.id.job_image)
        private val companyIcon: ImageView by bindView(R.id.company_icon)

        fun setUp(jobDetail: JobDetail) {
            this.title.text = jobDetail.title
            this.company.text = jobDetail.company.name
            this.companyUrl.text = jobDetail.company.url
            this.lookingFor.text = jobDetail.looking_for
            this.description.text = jobDetail.description
            this.lookingFor.text = jobDetail.looking_for

            if (jobDetail.image != null) {
                Glide.with(jobImage.context)
                        .load(jobDetail.image.i_304_124_x2)
                        .error(android.R.drawable.ic_delete)
                        .into(jobImage)
            }

            if (jobDetail.company.avatar != null) {
                Glide.with(companyIcon.context)
                        .load(jobDetail.company.avatar.original)
                        .error(android.R.drawable.ic_delete)
                        .into(companyIcon)
            }

            this.itemView.setOnClickListener { itemClick(jobDetail.title) }
        }
    }
}
