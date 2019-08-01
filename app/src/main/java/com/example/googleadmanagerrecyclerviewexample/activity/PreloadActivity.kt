package com.example.googleadmanagerrecyclerviewexample.activity

import android.os.Bundle
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.model.FeedItem
import com.example.googleadmanagerrecyclerviewexample.model.ShimmerItemModel

class PreloadActivity: FeedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shimmers = (0 until 4).map { ShimmerItemModel() as FeedItem } as ArrayList
        feedItems.postValue(shimmers)
        loadAds(20)
    }

    fun loadAds(count: Int){
        if (count == 0){
            createDataSource()
            combineItems()
            return
        }
        loadAd {
            if (it != null){
                mLoadedAds.add(FeedAdModel(it))
                loadAds(count -1)
            }
        }
    }

}