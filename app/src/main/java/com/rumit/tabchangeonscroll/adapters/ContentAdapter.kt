package com.rumit.tabchangeonscroll.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rumit.tabchangeonscroll.R
import com.rumit.tabchangeonscroll.databinding.ItemContentBinding
import com.rumit.tabchangeonscroll.databinding.ItemTitlesBinding
import com.rumit.tabchangeonscroll.models.ContentInfo

class ContentAdapter(var dataList: ArrayList<ContentInfo?>) :
    RecyclerView.Adapter<ContentAdapter.MyViewHolder>() {
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemContentBinding =
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentAdapter.MyViewHolder, position: Int) {
        if (holder is CategoryViewHolder) {
            holder.bind()
        }
    }

    inner class CategoryViewHolder(val binding: ItemContentBinding) : MyViewHolder(binding) {
        fun bind() {
            dataList[layoutPosition]?.let { model ->
                when (model.contentType) {
                    0 -> {

                        binding.testDataView0.tvContentTitle1.text = model.name

                        binding.testDataView0.llType0.visibility = View.VISIBLE
                        binding.testDataView1.llType1.visibility = View.GONE
                        binding.testDataView2.llType2.visibility = View.GONE
                    }

                    1 -> {
                        binding.testDataView1.tvContentTitle2.text = model.name

                        binding.testDataView0.llType0.visibility = View.GONE
                        binding.testDataView1.llType1.visibility = View.VISIBLE
                        binding.testDataView2.llType2.visibility = View.GONE
                    }

                    2 -> {
                        binding.testDataView2.tvContentTitle3.text = model.name

                        binding.testDataView0.llType0.visibility = View.GONE
                        binding.testDataView1.llType1.visibility = View.GONE
                        binding.testDataView2.llType2.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    open class MyViewHolder(val viewBinding: ItemContentBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}