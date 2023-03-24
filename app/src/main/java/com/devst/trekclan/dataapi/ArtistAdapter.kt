package com.devst.trekclan.dataapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devst.trekclan.R
import com.devst.trekclan.dataapi.ArtistDataClasses.Artist

class ArtistAdapter(private val list: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
    var listImages = listOf<Int>(
        R.drawable.fivem, R.drawable.fsix, R.drawable.onem, R.drawable.twom, R.drawable.fivem
    )


    lateinit var context: Context

    lateinit var listneeer: onItemClickListener

    interface onItemClickListener {
        fun onitemclickListner(position: Int)
    }

    fun setOnitemclick(listener: onItemClickListener) {
        listneeer = listener
    }


    class ViewHolder(item: View, listener: onItemClickListener) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById<ImageView>(R.id.genre_deatil_item_image)
        val genreTitle = item.findViewById<TextView>(R.id.genre_detail_item_title)
        val genredesc = item.findViewById<TextView>(R.id.genre_detail_item_descr)


        init {

            itemView.setOnClickListener {
                listener.onitemclickListner(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArtistAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.genre_detail_items, parent, false)
        context = item.context
        return ViewHolder(item, listneeer)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]

        holder.genreTitle.text = data.name
        holder.genredesc.text = data.streamable.toString()
        Glide.with(context).load(R.drawable.fourm).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
