package duggu.scroll.tabchange.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import duggu.scroll.tabchange.R
import duggu.scroll.tabchange.models.TitleInfo
import kotlinx.android.synthetic.main.item_titles.view.tvSubCatTextISF

class TitlesAdapter(
    val titles: ArrayList<TitleInfo?>,
    val onItemSelected: (selectedItemIndex: Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SubCategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_titles, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is SubCategoryViewHolder) {
            holder.bind()
        }
    }

    inner class SubCategoryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind() {
            titles[layoutPosition]?.let { model ->
                itemView.tvSubCatTextISF.text = model.titleName
                if (model.isSelected) {
                    itemView.tvSubCatTextISF.setTextColor(
                        ContextCompat.getColor(view.context, R.color.white)
                    )
                    itemView.tvSubCatTextISF.background =
                        ContextCompat.getDrawable(view.context, R.drawable.unslected_button)
                } else {
                    itemView.tvSubCatTextISF.setTextColor(
                        ContextCompat.getColor(view.context, R.color.black)
                    )
                    itemView.tvSubCatTextISF.background =
                        ContextCompat.getDrawable(view.context, R.drawable.selected_button)
                }

                itemView.tvSubCatTextISF.setOnClickListener {
                    onItemSelected(layoutPosition)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return titles.size
    }

    fun updateList(position: Int) {
        for ((index, model) in titles.withIndex()) {
            model?.isSelected = position == index
        }
        notifyDataSetChanged()
    }
}