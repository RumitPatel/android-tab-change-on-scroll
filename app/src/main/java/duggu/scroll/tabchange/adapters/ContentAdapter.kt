package duggu.scroll.tabchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import duggu.scroll.tabchange.R
import duggu.scroll.tabchange.models.ContentInfo
import kotlinx.android.synthetic.main.test_data_view_1.view.llType4
import kotlinx.android.synthetic.main.test_data_view_1.view.tvCatTextISF4
import kotlinx.android.synthetic.main.test_data_view_2.view.llType5
import kotlinx.android.synthetic.main.test_data_view_2.view.tvCatTextISF5
import kotlinx.android.synthetic.main.test_data_view_3.view.llType6
import kotlinx.android.synthetic.main.test_data_view_3.view.tvCatTextISF6

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
                        itemView.tvCatTextISF4.text = model.name

                        itemView.llType4.visibility = View.VISIBLE
                        itemView.llType5.visibility = View.GONE
                        itemView.llType6.visibility = View.GONE
                    }

                    1 -> {
                        itemView.tvCatTextISF5.text = model.name

                        itemView.llType4.visibility = View.GONE
                        itemView.llType5.visibility = View.VISIBLE
                        itemView.llType6.visibility = View.GONE
                    }

                    2 -> {
                        itemView.tvCatTextISF6.text = model.name

                        itemView.llType4.visibility = View.GONE
                        itemView.llType5.visibility = View.GONE
                        itemView.llType6.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
}