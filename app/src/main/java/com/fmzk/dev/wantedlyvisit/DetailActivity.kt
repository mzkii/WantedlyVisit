package com.fmzk.dev.wantedlyvisit

/**
 * Created by fmzk on 2017/10/24.
 */
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotterknife.bindView

class DetailActivity : AppCompatActivity() {
    private val textView: TextView by bindView(R.id.text_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        this.title = "DetailActivity"
        this.textView.text = intent.getStringExtra("itemName")
    }
}
