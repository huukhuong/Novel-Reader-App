package com.huukhuongit.novelreader.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huukhuongit.novelreader.R
import com.huukhuongit.novelreader.models.NovelModel
import com.squareup.picasso.Picasso

class AdapterLandscapeNovel(
    private var itemResource: Int,
    private var list: ArrayList<NovelModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<AdapterLandscapeNovel.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(itemResource, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val imgNovelThumb = holder.imgNovelThumb
        val txtNovelTitle = holder.txtNovelTitle
        val txtNovelAuthor = holder.txtNovelAuthor
        val txtNovelDescription = holder.txtNovelDescription

        Picasso.get().load(item.thumbnail).into(imgNovelThumb)
        txtNovelTitle.text = item.name
        txtNovelAuthor.text = item.author
        txtNovelDescription.text = item.description
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var imgNovelThumb: ImageView = itemView.findViewById(R.id.imgNovelThumb)
        var txtNovelTitle: TextView = itemView.findViewById(R.id.txtNovelTitle)
        var txtNovelAuthor: TextView = itemView.findViewById(R.id.txtNovelAuthor)
        var txtNovelDescription: TextView = itemView.findViewById(R.id.txtNovelDescription)

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
