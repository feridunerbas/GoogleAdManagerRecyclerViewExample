package com.example.googleadmanagerrecyclerviewexample

interface FeedItem {
    fun isEqualWith(feedItem: FeedItem): Boolean
}