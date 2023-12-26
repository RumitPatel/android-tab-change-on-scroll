package com.rumit.tabchangeonscroll

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.btnWithRecyclerView
import kotlinx.android.synthetic.main.activity_main.btnWithScrollView


class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        btnWithScrollView.setOnClickListener {
            startActivity(Intent(mContext, WIthScrollViewActivity::class.java))
        }

        btnWithRecyclerView.setOnClickListener {
            startActivity(Intent(mContext, WIthRecyclerViewActivity::class.java))
        }
    }
}
