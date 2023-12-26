package com.rumit.tabchangeonscroll.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rumit.tabchangeonscroll.R
import com.rumit.tabchangeonscroll.databinding.ItemTitlesBinding
import com.rumit.tabchangeonscroll.models.TitleInfo

class TitlesAdapter(
    val mContext: Context,
    val titles: ArrayList<TitleInfo?>?,
    val onItemSelected: (selectedItemIndex: Int) -> Unit
) : RecyclerView.Adapter<TitlesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding: ItemTitlesBinding =
            ItemTitlesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return MyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val model: TitleInfo? = titles?.get(holder.absoluteAdapterPosition)

        if (model != null) {
            holder.viewBinding.tvSubCatTextISF.text = model.titleName

            if (model.isSelected) {
                holder.viewBinding.tvSubCatTextISF.setTextColor(
                    ContextCompat.getColor(mContext, R.color.white)
                )
                holder.viewBinding.tvSubCatTextISF.background =
                    ContextCompat.getDrawable(mContext, R.drawable.unslected_button)
            } else {
                holder.viewBinding.tvSubCatTextISF.setTextColor(
                    ContextCompat.getColor(mContext, R.color.black)
                )
                holder.viewBinding.tvSubCatTextISF.background =
                    ContextCompat.getDrawable(mContext, R.drawable.selected_button)
            }

            holder.viewBinding.tvSubCatTextISF.setOnClickListener {
                onItemSelected(holder.absoluteAdapterPosition)
            }
        }
    }


    override fun getItemCount(): Int {
        return titles?.size ?: 0
    }

    fun updateList(position: Int) {
        for ((index, model) in titles?.withIndex()!!) {
            model?.isSelected = position == index
        }
        notifyDataSetChanged()
    }

    open class MyViewHolder(val viewBinding: ItemTitlesBinding) :
        RecyclerView.ViewHolder(viewBinding.root)
}