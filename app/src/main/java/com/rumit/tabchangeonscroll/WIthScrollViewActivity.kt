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
import com.rumit.tabchangeonscroll.util.TAG
import com.rumit.tabchangeonscroll.util.getTitles
import kotlinx.android.synthetic.main.activity_with_scrollview.sv
import kotlinx.android.synthetic.main.activity_with_scrollview.tvTitles
import kotlinx.android.synthetic.main.test_data_view_1.llType0
import kotlinx.android.synthetic.main.test_data_view_1.tvContentTitle1
import kotlinx.android.synthetic.main.test_data_view_2.llType1
import kotlinx.android.synthetic.main.test_data_view_2.tvContentTitle2
import kotlinx.android.synthetic.main.test_data_view_3.llType2
import kotlinx.android.synthetic.main.test_data_view_3.tvContentTitle3


class WIthScrollViewActivity : AppCompatActivity() {

    private lateinit var mContext: Context

    private var titlesAdapter: TitlesAdapter? = null
    private var selectedTitlePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_scrollview)
        mContext = this

        tvTitles.apply {
            layoutManager = LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false)
            titlesAdapter = TitlesAdapter(getTitles()) {
                selectedTitlePosition = it
                setTitleSelected()

                when (selectedTitlePosition) {
                    0 -> {
                        sv.scrollTo(0, llType0.top)
                    }

                    1 -> {
                        sv.scrollTo(0, llType1.top)
                    }

                    2 -> {
                        sv.scrollTo(0, llType2.top)
                    }
                }
            }
            adapter = titlesAdapter
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val scrollBounds = Rect()
            sv.getHitRect(scrollBounds)

            sv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (tvContentTitle1 != null) {
                    if (tvContentTitle1.getLocalVisibleRect(scrollBounds)) {
                        if (!tvContentTitle1.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvContentTitle1.height
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
                }

                if (tvContentTitle2 != null) {
                    if (tvContentTitle2.getLocalVisibleRect(scrollBounds)) {
                        if (!tvContentTitle2.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvContentTitle2.height
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
                }

                if (tvContentTitle3 != null) {
                    if (tvContentTitle3.getLocalVisibleRect(scrollBounds)) {
                        if (!tvContentTitle3.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvContentTitle3.height
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
                }
            })
        }
    }

    private fun setTitleSelected() {
        titlesAdapter?.updateList(selectedTitlePosition)
    }
}