package com.example.googleadmanagerrecyclerviewexample

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class FeedItemsAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = ArrayList<FeedItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            0 -> FeedViewHolder(parent)
            else -> AdViewHolder(parent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        if(items[position] is FeedModel){
            return 0
        }
        return 1

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = items[position]
        (model as? FeedModel)?.let {
            (holder as? FeedViewHolder)?.bind(it)
        }
        (model as? FeedAdModel)?.let {
            (holder as? AdViewHolder)?.bind(it)
        }

    }

    fun updateDataSource(newItems: ArrayList<FeedItem>) {
        val diffResult = DiffUtil.calculateDiff(FeedDiffCallback(this.items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

}