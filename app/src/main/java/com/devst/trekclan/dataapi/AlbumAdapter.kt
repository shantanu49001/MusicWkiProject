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
import com.devst.trekclan.dataapi.AlbumDataClasses.Album
import java.util.*

class AlbumAdapter(private val list: List<Album>) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var listImages = listOf<Int>(
        R.drawable.fivem, R.drawable.fsix, R.drawable.onem, R.drawable.twom, R.drawable.fivem
    )


    lateinit var context: Context

    lateinit var listneer: onItemClickListener

    interface onItemClickListener {
        fun onitemclickListner(position: Int)
    }

    fun setOnitemclick(listener: onItemClickListener) {
        listneer = listener
    }


    class ViewHolder(item: View, listener: onItemClickListener) : RecyclerView.ViewHolder(item) {
        val image = item.findViewById<ImageView>(R.id.genre_deatil_item_image)
        val genreTitle = item.findViewById<TextView>(R.id.genre_detail_item_title)
        val genredesc = item.findViewById<TextView>(R.id.genre_detail_item_descr)


        init {

            //flows from here listner snd position to unimpemted function of interface
            itemView.setOnClickListener {
                listener.onitemclickListner(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.genre_detail_items, parent, false)
        context = item.context
        return ViewHolder(item, listneer)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.ViewHolder, position: Int) {
        val data = list[position]
        //   val randomIndex = kotlin.random.Random.nextInt(listImages.size)-1;


        //  val image_=listImages[randomIndex]
        holder.genreTitle.text = data.artist.name
        holder.genredesc.text = data.name
        Glide.with(context).load(R.drawable.fivem).into(holder.image)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}