package com.rumit.tabchangeonscroll.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumit.tabchangeonscroll.databinding.ItemContentBinding
import com.rumit.tabchangeonscroll.models.ContentInfo

class ContentAdapter(var dataList: ArrayList<ContentInfo?>?) :
    RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemContentBinding =
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: ContentInfo? = dataList?.get(holder.absoluteAdapterPosition)

        if (model != null) {
            when (model.contentType) {
                0 -> {
                    holder.viewBinding.testDataView0.tvContentTitle1.text = model.name

                    holder.viewBinding.testDataView0.llType0.visibility = View.VISIBLE
                    holder.viewBinding.testDataView1.llType1.visibility = View.GONE
                    holder.viewBinding.testDataView2.llType2.visibility = View.GONE
                }

                1 -> {
                    holder.viewBinding.testDataView1.tvContentTitle2.text = model.name

                    holder.viewBinding.testDataView0.llType0.visibility = View.GONE
                    holder.viewBinding.testDataView1.llType1.visibility = View.VISIBLE
                    holder.viewBinding.testDataView2.llType2.visibility = View.GONE
                }

                2 -> {
                    holder.viewBinding.testDataView2.tvContentTitle3.text = model.name

                    holder.viewBinding.testDataView0.llType0.visibility = View.GONE
                    holder.viewBinding.testDataView1.llType1.visibility = View.GONE
                    holder.viewBinding.testDataView2.llType2.visibility = View.VISIBLE
                }
            }
            holder.viewBinding.testDataView0.tvContentTitle1.text = model.name


        }
    }

    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }

    open class MyViewHolder(val viewBinding: ItemContentBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}