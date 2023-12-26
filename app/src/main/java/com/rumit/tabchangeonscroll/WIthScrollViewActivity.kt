package com.rumit.tabchangeonscroll

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.rumit.tabchangeonscroll.adapters.TitlesAdapter
import com.rumit.tabchangeonscroll.databinding.ActivityWithScrollviewBinding
import com.rumit.tabchangeonscroll.util.TAG
import com.rumit.tabchangeonscroll.util.getTitles


class WIthScrollViewActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityWithScrollviewBinding

    private var titlesAdapter: TitlesAdapter? = null
    private var selectedTitlePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithScrollviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mContext = this

        binding.tvTitles.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            titlesAdapter = TitlesAdapter(mContext, getTitles()) {
                selectedTitlePosition = it
                setTitleSelected()

                when (selectedTitlePosition) {
                    0 -> {
                        binding.sv.scrollTo(0, binding.testDataView0.llType0.top)
                    }

                    1 -> {
                        binding.sv.scrollTo(0, binding.testDataView1.llType1.top)
                    }

                    2 -> {
                        binding.sv.scrollTo(0, binding.testDataView2.llType2.top)
                    }
                }
            }
            adapter = titlesAdapter
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val scrollBounds = Rect()
            binding.sv.getHitRect(scrollBounds)

            binding.sv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (binding.testDataView0.tvContentTitle1.getLocalVisibleRect(scrollBounds)) {
                    if (!binding.testDataView0.tvContentTitle1.getLocalVisibleRect(scrollBounds)
                        || scrollBounds.height() < binding.testDataView0.tvContentTitle1.height
                    ) {
                        Log.e(TAG, "tvContentTitle1 displayed partial portion")
                    } else {
                        Log.e(TAG, "tvContentTitle1 displayed full portion")
                    }
                    selectedTitlePosition = 0
                    setTitleSelected()

                } else {
                    Log.e(TAG, "tvCatTextISF4 No")
                }

                if (binding.testDataView1.tvContentTitle2.getLocalVisibleRect(scrollBounds)) {
                    if (!binding.testDataView1.tvContentTitle2.getLocalVisibleRect(scrollBounds)
                        || scrollBounds.height() < binding.testDataView1.tvContentTitle2.height
                    ) {
                        Log.e(TAG, "tvContentTitle2 displayed partial portion")
                    } else {
                        Log.e(TAG, "tvContentTitle2 displayed full portion")
                    }
                    selectedTitlePosition = 1
                    setTitleSelected()

                } else {
                    Log.e(TAG, "tvCatTextISF5 No")
                }

                if (binding.testDataView2.tvContentTitle3.getLocalVisibleRect(scrollBounds)) {
                    if (!binding.testDataView2.tvContentTitle3.getLocalVisibleRect(scrollBounds)
                        || scrollBounds.height() < binding.testDataView2.tvContentTitle3.height
                    ) {
                        Log.e(TAG, "tvContentTitle3 displayed partial portion")
                    } else {
                        Log.e(TAG, "tvContentTitle3 displayed full portion")
                    }
                    selectedTitlePosition = 2
                    setTitleSelected()

                } else {
                    Log.e(TAG, "tvCatTextISF6 No")
                }
            })
        }
    }

    private fun setTitleSelected() {
        titlesAdapter?.updateList(selectedTitlePosition)
    }
}