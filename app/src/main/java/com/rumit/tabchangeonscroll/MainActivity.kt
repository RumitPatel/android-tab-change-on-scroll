package com.rumit.tabchangeonscroll

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rumit.tabchangeonscroll.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this


        binding.btnWithScrollView.setOnClickListener {
            startActivity(Intent(mContext, WIthScrollViewActivity::class.java))
        }

        binding.btnWithRecyclerView.setOnClickListener {
            startActivity(Intent(mContext, WIthRecyclerViewActivity::class.java))
        }
    }
}