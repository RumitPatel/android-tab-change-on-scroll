package duggu.scroll.tabchange

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import duggu.scroll.tabchange.adapters.TitlesAdapter
import duggu.scroll.tabchange.models.ContentInfo
import duggu.scroll.tabchange.util.getTitles
import kotlinx.android.synthetic.main.activity_with_scrollview.llType4
import kotlinx.android.synthetic.main.activity_with_scrollview.llType5
import kotlinx.android.synthetic.main.activity_with_scrollview.llType6
import kotlinx.android.synthetic.main.activity_with_scrollview.sv
import kotlinx.android.synthetic.main.activity_with_scrollview.tvCatTextISF4
import kotlinx.android.synthetic.main.activity_with_scrollview.tvCatTextISF5
import kotlinx.android.synthetic.main.activity_with_scrollview.tvCatTextISF6
import kotlinx.android.synthetic.main.activity_with_scrollview.tvTitles


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

                if (selectedTitlePosition == 0) {
//                    llType6.parent.requestChildFocus(llType4,llType4);
                    sv.scrollTo(0, llType4.top)
                } else if (selectedTitlePosition == 1) {
//                    llType6.parent.requestChildFocus(llType5,llType5);
                    sv.scrollTo(0, llType5.top)
                } else if (selectedTitlePosition == 2) {
//                    llType6.parent.requestChildFocus(llType6,llType6);
                    sv.scrollTo(0, llType6.top)
                }
            }
            adapter = titlesAdapter


        }



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val scrollBounds = Rect()
            sv.getHitRect(scrollBounds)

            sv.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (tvCatTextISF4 != null) {
                    if (tvCatTextISF4.getLocalVisibleRect(scrollBounds)) {
                        if (!tvCatTextISF4.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvCatTextISF4.height
                        ) {
                            Log.e("RUM", "tvCatTextISF4 APPEAR PARCIALY")
                        } else {
                            Log.e("RUM", "tvCatTextISF4 APPEAR FULLY!!!")
                        }
                        selectedTitlePosition = 0
                        setTitleSelected()

                    } else {
                        Log.e("RUM", "tvCatTextISF4 No")
                    }
                }

                if (tvCatTextISF5 != null) {
                    if (tvCatTextISF5.getLocalVisibleRect(scrollBounds)) {
                        if (!tvCatTextISF5.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvCatTextISF5.height
                        ) {
                            Log.e("RUM", "tvCatTextISF5 APPEAR PARCIALY")
                        } else {
                            Log.e("RUM", "tvCatTextISF5 APPEAR FULLY!!!")
                        }
                        selectedTitlePosition = 1
                        setTitleSelected()

                    } else {
                        Log.e("RUM", "tvCatTextISF5 No")
                    }
                }

                if (tvCatTextISF6 != null) {
                    if (tvCatTextISF6.getLocalVisibleRect(scrollBounds)) {
                        if (!tvCatTextISF6.getLocalVisibleRect(scrollBounds)
                            || scrollBounds.height() < tvCatTextISF6.height
                        ) {
                            Log.e("RUM", "tvCatTextISF6 APPEAR PARCIALY")
                        } else {
                            Log.e("RUM", "tvCatTextISF6 APPEAR FULLY!!!")
                        }
                        selectedTitlePosition = 2
                        setTitleSelected()

                    } else {
                        Log.e("RUM", "tvCatTextISF6 No")
                    }
                }
            })
        }

    }

    private fun getCatModel(type: Int, model: String, index: Int): ArrayList<ContentInfo> {
        val contentInfoList = ArrayList<ContentInfo>()
        contentInfoList.add(ContentInfo(type, model, index))
        return contentInfoList
    }

    private fun setTitleSelected() {
        titlesAdapter?.updateList(selectedTitlePosition)
    }


}

