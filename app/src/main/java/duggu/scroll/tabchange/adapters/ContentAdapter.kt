package duggu.scroll.tabchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import duggu.scroll.tabchange.R
import duggu.scroll.tabchange.models.ContentInfo
import kotlinx.android.synthetic.main.item_content.view.llType1
import kotlinx.android.synthetic.main.item_content.view.llType2
import kotlinx.android.synthetic.main.item_content.view.llType3
import kotlinx.android.synthetic.main.item_content.view.tvCatTextISF1
import kotlinx.android.synthetic.main.item_content.view.tvCatTextISF2
import kotlinx.android.synthetic.main.item_content.view.tvCatTextISF3

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
                if (model.contentType == 0) {
                    itemView.tvCatTextISF1.text = model.name

                    itemView.llType1.visibility = View.VISIBLE
                    itemView.llType2.visibility = View.GONE
                    itemView.llType3.visibility = View.GONE
                } else if (model.contentType == 1) {
                    itemView.tvCatTextISF2.text = model.name

                    itemView.llType1.visibility = View.GONE
                    itemView.llType2.visibility = View.VISIBLE
                    itemView.llType3.visibility = View.GONE
                } else if (model.contentType == 2) {
                    itemView.tvCatTextISF3.text = model.name

                    itemView.tvCatTextISF3.visibility = View.VISIBLE

                    itemView.llType1.visibility = View.GONE
                    itemView.llType2.visibility = View.GONE
                    itemView.llType3.visibility = View.VISIBLE
                }


            }
        }
    }
}