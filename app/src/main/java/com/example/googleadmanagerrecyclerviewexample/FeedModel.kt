package com.example.googleadmanagerrecyclerviewexample

import java.util.*

data class FeedModel(val id: String, val title: String, val imageUrl: String, val width: Int, val height: Int): FeedItem {

    override fun isEqualWith(feedItem: FeedItem): Boolean {
        (feedItem as? FeedModel)?.let {
            return id == it.id
        }
        return false
    }

    companion object{
        fun random(): FeedModel{
            val id = UUID.randomUUID().toString()
            val width = (200 until 400).random()
            val height = (200 until 400).random()
            val url = "https://picsum.photos/$width/$height"
            return FeedModel(id,"".loremIpsum(),url,width,height)
        }
    }

    val aspectRatio: Double
    get(){
        return width.toDouble() / height.toDouble()
    }
}
