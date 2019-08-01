package com.example.googleadmanagerrecyclerviewexample.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.googleadmanagerrecyclerviewexample.adapter.diff.FeedDiffCallback
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.model.FeedItem
import com.example.googleadmanagerrecyclerviewexample.model.FeedModel
import com.example.googleadmanagerrecyclerviewexample.adapter.viewHolder.AdViewHolder
import com.example.googleadmanagerrecyclerviewexample.adapter.viewHolder.FeedViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.googleadmanagerrecyclerviewexample.adapter.viewHolder.ShimmerViewHolder
import com.example.googleadmanagerrecyclerviewexample.model.ShimmerItemModel


class FeedItemsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<FeedItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> FeedViewHolder(parent)
            1 -> AdViewHolder(parent)
            else -> ShimmerViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if(items[position] is FeedModel){
            return 0
        }else if(items[position] is FeedAdModel){
            return 1
        }
        return 2

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        (model as? FeedModel)?.let {
            (holder as? FeedViewHolder)?.bind(it)
        }
        (model as? FeedAdModel)?.let {
            (holder as? AdViewHolder)?.bind(it)
            (holder.itemView.layoutParams as? StaggeredGridLayoutManager.LayoutParams)?.isFullSpan = it.ad.videoController.hasVideoContent()

        }
        (model as? ShimmerItemModel)?.let {
            (holder as? ShimmerViewHolder)?.bind(it)
        }

    }

    fun updateDataSource(newItems: ArrayList<FeedItem>) {
        val diffResult = DiffUtil.calculateDiff(
            FeedDiffCallback(
                this.items,
                newItems
            )
        )
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}