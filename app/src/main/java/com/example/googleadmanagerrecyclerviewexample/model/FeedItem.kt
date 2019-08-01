package com.example.googleadmanagerrecyclerviewexample.model

interface FeedItem {
    fun isEqualWith(feedItem: FeedItem): Boolean
}