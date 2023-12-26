package com.rumit.tabchangeonscroll.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumit.tabchangeonscroll.R
import com.rumit.tabchangeonscroll.models.ContentInfo
import kotlinx.android.synthetic.main.test_data_view_1.view.llType0
import kotlinx.android.synthetic.main.test_data_view_1.view.tvContentTitle1
import kotlinx.android.synthetic.main.test_data_view_2.view.llType1
import kotlinx.android.synthetic.main.test_data_view_2.view.tvContentTitle2
import kotlinx.android.synthetic.main.test_data_view_3.view.llType2
import kotlinx.android.synthetic.main.test_data_view_3.view.tvContentTitle3

class ContentAdapter(var dataList: ArrayList<ContentInfo?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CategoryViewHolder) {
            holder.bind()
        }
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            dataList[layoutPosition]?.let { model ->
                when (model.contentType) {
                    0 -> {
                        itemView.tvContentTitle1.text = model.name

                        itemView.llType0.visibility = View.VISIBLE
                        itemView.llType1.visibility = View.GONE
                        itemView.llType2.visibility = View.GONE
                    }

                    1 -> {
                        itemView.tvContentTitle2.text = model.name

                        itemView.llType0.visibility = View.GONE
                        itemView.llType1.visibility = View.VISIBLE
                        itemView.llType2.visibility = View.GONE
                    }

                    2 -> {
                        itemView.tvContentTitle3.text = model.name

                        itemView.llType0.visibility = View.GONE
                        itemView.llType1.visibility = View.GONE
                        itemView.llType2.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}