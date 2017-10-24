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

class ItemListAdapter(private val items: List<JobDetail>, private val itemClick: (String) -> Unit) :
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

        fun setUp(jobDetail: JobDetail) {
            this.jobTitle.text = jobDetail.title
            this.company.text = jobDetail.company.name
            this.lookingFor.text = jobDetail.looking_for

            Glide.with(jobImage.context)
                    .load(jobDetail.image.original)
                    .placeholder(android.R.drawable.ic_delete)
                    .error(android.R.drawable.ic_delete)
                    .dontAnimate()
                    .into(jobImage)

            this.itemView.setOnClickListener { itemClick(jobDetail.title) }
        }
    }
}
