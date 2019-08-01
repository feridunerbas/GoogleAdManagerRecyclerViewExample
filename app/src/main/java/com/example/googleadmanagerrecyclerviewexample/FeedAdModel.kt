package com.example.googleadmanagerrecyclerviewexample

import com.google.android.gms.ads.formats.UnifiedNativeAd
import java.util.*

class FeedAdModel(val ad: UnifiedNativeAd): FeedItem {

    private val id = UUID.randomUUID().toString()

    override fun isEqualWith(feedItem: FeedItem): Boolean {
        (feedItem as? FeedAdModel)?.let {
            return id == it.id
        }
        return false
    }
}