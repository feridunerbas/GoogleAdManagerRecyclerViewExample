package com.example.googleadmanagerrecyclerviewexample

import androidx.recyclerview.widget.DiffUtil

class FeedDiffCallback(private var newItems: ArrayList<FeedItem>, private var oldItems: ArrayList<FeedItem>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].isEqualWith(newItems[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].isEqualWith(newItems[newItemPosition])
    }

}