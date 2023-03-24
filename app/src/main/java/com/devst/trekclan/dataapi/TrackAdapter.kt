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
import com.devst.trekclan.dataapi.TracksDataClasses.TrackLow

class TrackAdapter(private val listtrack:List<TrackLow>):RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    lateinit var context: Context

    lateinit var listnertr:onItemClickListener

    interface onItemClickListener{
        fun onitemclickListner(position: Int)
    }

    fun setOnitemclick(listener: onItemClickListener){
        listnertr=listener
    }




    class ViewHolder(item: View, listener: onItemClickListener): RecyclerView.ViewHolder(item) {
        val image=item.findViewById<ImageView>(R.id.genre_deatil_item_image)
        val genreTitle=item.findViewById<TextView>(R.id.genre_detail_item_title)
        val genredesc=item.findViewById<TextView>(R.id.genre_detail_item_descr)


        init {


            itemView.setOnClickListener {
                listener.onitemclickListner(adapterPosition)
            }
        }



    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrackAdapter.ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val item=inflater.inflate(R.layout.genre_detail_items,parent,false)
        context=item.context
        return ViewHolder(item,listnertr)
    }

    override fun onBindViewHolder(holder: TrackAdapter.ViewHolder, position: Int) {
        val data=listtrack[position]

        holder.genreTitle.text=data.artist.name
        holder.genredesc.text=data.name
        Glide.with(context).load(R.drawable.fourm).into(holder.image)
    }

    override fun getItemCount(): Int {
        return listtrack.size
    }
}
