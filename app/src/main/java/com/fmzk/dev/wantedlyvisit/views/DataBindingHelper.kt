package com.fmzk.dev.wantedlyvisit.views

import android.databinding.BindingAdapter
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.fmzk.dev.wantedlyvisit.R


/**
 * Created by fmzk on 2017/10/30.
 */
object DataBindingHelper {

    @JvmStatic
    @BindingAdapter("imageSrc")
    fun ImageView.imageSrc(imageUrl: String) {
        if (imageUrl.isNotEmpty()) {
            Glide.with(this.context)
                    .load(imageUrl)
                    .error(android.R.drawable.ic_delete)
                    .into(this)
            this.scaleType = ImageView.ScaleType.CENTER_CROP
        } else {
            val bg = ContextCompat.getDrawable(this.context, R.drawable.image_placeholder)
            bg?.setColorFilter(ContextCompat.getColor(this.context, R.color.dark_red), PorterDuff.Mode.OVERLAY)
            this.background = bg
        }
    }
}