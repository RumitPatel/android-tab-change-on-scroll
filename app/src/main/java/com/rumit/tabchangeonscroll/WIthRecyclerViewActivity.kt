package com.rumit.tabchangeonscroll

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rumit.tabchangeonscroll.adapters.ContentAdapter
import com.rumit.tabchangeonscroll.adapters.TitlesAdapter
import com.rumit.tabchangeonscroll.databinding.ActivityWithRecyclerviewBinding
import com.rumit.tabchangeonscroll.models.ContentInfo
import com.rumit.tabchangeonscroll.util.getTitles


class WIthRecyclerViewActivity : AppCompatActivity() {

    private lateinit var mContext: Context
    private lateinit var binding: ActivityWithRecyclerviewBinding

    private var linearLayoutManager: LinearLayoutManager? = null
    private var titlesAdapter: TitlesAdapter? = null
    private var contentAdapter: ContentAdapter? = null
    private var selectedTitlePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWithRecyclerviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mContext = this

        val contentInfo = ArrayList<ContentInfo?>()
        contentInfo.addAll(getCatModel(0, "Scientific Classification", 0))
        contentInfo.addAll(getCatModel(1, "Overview Content", 1))
        contentInfo.addAll(getCatModel(2, "Description Content", 2))


        binding.tvTitles.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            titlesAdapter = TitlesAdapter(mContext, getTitles()) {
                selectedTitlePosition = it
                setTitleSelected()
                binding.rvServiceList.layoutManager?.scrollToPosition(selectedTitlePosition)
            }
            adapter = titlesAdapter
        }

        linearLayoutManager = LinearLayoutManager(this)
        binding.rvServiceList.apply {
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
                                    binding.tvTitles.layoutManager?.scrollToPosition(
                                        selectedTitlePosition
                                    )
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