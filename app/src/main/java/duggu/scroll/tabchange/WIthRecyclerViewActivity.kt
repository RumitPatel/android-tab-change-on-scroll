package duggu.scroll.tabchange

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import duggu.scroll.tabchange.adapters.ContentAdapter
import duggu.scroll.tabchange.adapters.TitlesAdapter
import duggu.scroll.tabchange.models.ContentInfo
import duggu.scroll.tabchange.util.getTitles
import kotlinx.android.synthetic.main.activity_with_recyclerview.rvServiceList
import kotlinx.android.synthetic.main.activity_with_recyclerview.tvTitles


class WIthRecyclerViewActivity : AppCompatActivity() {

    private var linearLayoutManager: LinearLayoutManager? = null
    private var titlesAdapter: TitlesAdapter? = null
    private var contentAdapter: ContentAdapter? = null
    private var selectedTitlePosition: Int = 0

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
                rvServiceList.layoutManager?.scrollToPosition(selectedTitlePosition)
            }
            adapter = titlesAdapter
        }

        linearLayoutManager = LinearLayoutManager(this)
        rvServiceList.apply {
            layoutManager = linearLayoutManager
            contentAdapter = ContentAdapter(contentInfo)
            adapter = contentAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    linearLayoutManager?.let { lm ->
                        val index = lm.findFirstVisibleItemPosition()
                        if ((contentInfo.size - 1) >= index) {
                            contentInfo[index]?.let { sd ->
                                if (selectedTitlePosition != sd.index) {
                                    selectedTitlePosition = sd.index
                                    setTitleSelected()
                                    tvTitles.layoutManager?.scrollToPosition(selectedTitlePosition)
                                }
                            }
                        }
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