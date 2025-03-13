package com.example.cubtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cubtest.R
import com.example.cubtest.data.model.Attraction

class AttractionAdapter(
    private val attractions: List<Attraction>,
    private val onItemClick: (Attraction) -> Unit
) : RecyclerView.Adapter<AttractionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textViewListName: TextView = view.findViewById(R.id.textview_list_name)
        val textViewListIntro: TextView = view.findViewById(R.id.textview_list_intro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_attraction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val attraction = attractions[position]
        holder.textViewListName.text = attraction.name
        holder.textViewListIntro.text = attraction.introduction

        if (attraction.images.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(attraction.images[0].src)
                .error(R.drawable.broken_image)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(R.drawable.broken_image)
            holder.imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        }

        holder.itemView.setOnClickListener { onItemClick(attraction) }
    }

    override fun getItemCount() = attractions.size
}