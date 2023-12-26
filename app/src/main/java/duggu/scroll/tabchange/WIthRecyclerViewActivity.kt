package duggu.scroll.tabchange

import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import duggu.scroll.tabchange.adapters.ContentAdapter
import duggu.scroll.tabchange.adapters.TitlesAdapter
import duggu.scroll.tabchange.models.ContentInfo
import duggu.scroll.tabchange.util.getTitles
import kotlinx.android.synthetic.main.activity_with_recyclerview.llType4
import kotlinx.android.synthetic.main.activity_with_recyclerview.llType5
import kotlinx.android.synthetic.main.activity_with_recyclerview.llType6
import kotlinx.android.synthetic.main.activity_with_recyclerview.rvServiceList
import kotlinx.android.synthetic.main.activity_with_recyclerview.sv
import kotlinx.android.synthetic.main.activity_with_recyclerview.tvCatTextISF4
import kotlinx.android.synthetic.main.activity_with_recyclerview.tvCatTextISF5
import kotlinx.android.synthetic.main.activity_with_recyclerview.tvCatTextISF6
import kotlinx.android.synthetic.main.activity_with_recyclerview.tvTitles


class WIthRecyclerViewActivity : AppCompatActivity() {

    private var linearLayoutManager: LinearLayoutManager? = null
    private var titlesAdapter: TitlesAdapter? = null
    private var contentAdapter: ContentAdapter? = null
    private var selectedTitlePosition: Int = 0

    /*private var smoothScroller: SmoothScroller = object : LinearSmoothScroller(this.baseContext) {
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }*/

    private fun RecyclerView.smoothSnapToPosition(
        position: Int,
        snapMode: Int = LinearSmoothScroller.SNAP_TO_START
    ) {
        val smoothScroller = object : LinearSmoothScroller(this.context) {
            override fun getVerticalSnapPreference(): Int = snapMode
            override fun getHorizontalSnapPreference(): Int = snapMode
        }
        smoothScroller.targetPosition = position
        layoutManager?.startSmoothScroll(smoothScroller)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_with_recyclerview)

        val contentInfo = ArrayList<ContentInfo?>()
        contentInfo.addAll(getCatModel(0, "Scientific Classification", 0))
        contentInfo.addAll(getCatModel(1, "Overview Content", 1))
        contentInfo.addAll(getCatModel(2, "Description Content", 2))


        tvTitles.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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


//                rvServiceList.layoutManager?.scrollToPosition(selectedTitlePosition)

//                rvServiceList.layoutManager?.smoothScrollToPosition(rvServiceList, RecyclerView.State(), selectedTitlePosition)


//                rvServiceList.smoothSnapToPosition(selectedTitlePosition)

                /*                smoothScroller.targetPosition = selectedTitlePosition;
                                rvServiceList.layoutManager?.startSmoothScroll(smoothScroller);*/
            }
            adapter = titlesAdapter

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

        linearLayoutManager = LinearLayoutManager(this)
        rvServiceList.apply {
            layoutManager = linearLayoutManager
            contentAdapter = ContentAdapter(contentInfo)
            adapter = contentAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    if(isSelectSubCatScrollClick) {
                    linearLayoutManager?.let { lm ->
                        val index = lm.findFirstVisibleItemPosition()
                        if ((contentInfo.size - 1) >= index) {
                            contentInfo[index]?.let { sd ->
                                if (selectedTitlePosition != sd.index) {
                                    selectedTitlePosition = sd.index
                                    setTitleSelected()
                                    tvTitles.layoutManager?.scrollToPosition(
                                        selectedTitlePosition
                                    )
                                }
                            }
                        }
                    }
//                    }
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

