package com.example.googleadmanagerrecyclerviewexample.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.googleadmanagerrecyclerviewexample.R
import com.example.googleadmanagerrecyclerviewexample.model.ShimmerItemModel
import kotlinx.android.synthetic.main.layout_feed_shimmer_item.view.*

class ShimmerViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.layout_feed_shimmer_item,parent,false)) {

    fun bind(feedModel: ShimmerItemModel){
        itemView.shimmerContainer.repeatCount = 30
        itemView.shimmerContainer.startShimmerAnimation()

    }
}