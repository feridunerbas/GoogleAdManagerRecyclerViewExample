package com.example.googleadmanagerrecyclerviewexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_feed_item.view.*

class FeedViewHolder(private val parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_feed_item,parent,false)) {
    fun bind(feedModel: FeedModel){

        itemView.textView.text = feedModel.title
        Glide.with(parent.context).load(feedModel.imageUrl).into(itemView.imageView)

    }
}