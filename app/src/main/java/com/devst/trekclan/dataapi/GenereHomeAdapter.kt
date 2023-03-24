package com.devst.trekclan.dataapi

import android.content.Context
import android.widget.TextView
import com.devst.trekclan.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devst.trekclan.dataapi.GenreDataClasses.Tag

class GenereHomeAdapter(private val list: List<Tag>) :
    RecyclerView.Adapter<GenereHomeAdapter.ViewHolder>() {

    lateinit var context: Context

    //1
    interface onItemClickListner {
        fun onItemClick(position: Int)
    }

    //2
    private lateinit var mListener: onItemClickListner

    //3
    fun setOnItemClickListner(listner: onItemClickListner) {
        mListener = listner
    }

    //4
    inner class ViewHolder(item: View, listner: onItemClickListner) :
        RecyclerView.ViewHolder(item) {
        val genrename = item.findViewById<TextView>(R.id.genere_name)

        //5
        init {
            itemView.setOnClickListener {
                listner.onItemClick(adapterPosition)


            }

        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenereHomeAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = inflater.inflate(R.layout.genreitem, parent, false)
        context = item.context
        return ViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GenereHomeAdapter.ViewHolder, position: Int) {
        val item = list[position]
        holder.genrename.text = item.name


    }

    override fun getItemCount(): Int {
        return list.size
    }
}


