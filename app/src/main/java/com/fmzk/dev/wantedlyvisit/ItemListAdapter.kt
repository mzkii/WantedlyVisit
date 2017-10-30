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
import com.fmzk.dev.wantedlyvisit.models.JobDetail
import kotterknife.bindView


class ItemListAdapter(private val items: ArrayList<JobDetail>, private val itemClick: (JobDetail) -> Unit) :
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

    class ViewHolder(view: View, val itemClick: (JobDetail) -> Unit) : RecyclerView.ViewHolder(view) {
        private val title: TextView by bindView(R.id.title)
        private val lookingFor: TextView by bindView(R.id.looking_for)
        private val description: TextView by bindView(R.id.description)
        private val jobImage: ImageView by bindView(R.id.job_image)

        fun setUp(jobDetail: JobDetail) {
            this.title.text = jobDetail.title
            this.lookingFor.text = jobDetail.lookingFor
            this.description.text = jobDetail.description
            this.lookingFor.text = jobDetail.lookingFor

            if (jobDetail.image != null) {
                Glide.with(jobImage.context)
                        .load(jobDetail.image.i_304_124_x2)
                        .error(android.R.drawable.ic_delete)
                        .into(jobImage)
            }

            this.itemView.setOnClickListener { itemClick(jobDetail) }
        }
    }
}
