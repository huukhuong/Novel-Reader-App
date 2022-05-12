package com.huukhuongit.novelreader.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.models.CategoryModel

class AdapterCategory(
    private var list: ArrayList<CategoryModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val txtCategoryName = holder.txtCategoryName

        txtCategoryName.text = item.name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var txtCategoryName: TextView = itemView.findViewById(R.id.txtCategoryName)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, list[position])
            }
        }

    }
}
