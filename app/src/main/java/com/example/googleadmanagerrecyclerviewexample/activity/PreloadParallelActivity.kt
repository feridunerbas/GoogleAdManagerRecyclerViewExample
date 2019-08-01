package com.example.googleadmanagerrecyclerviewexample.activity

import android.os.Bundle
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.model.FeedItem
import com.example.googleadmanagerrecyclerviewexample.model.ShimmerItemModel

class PreloadParallelActivity: FeedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shimmers = (0 until 4).map { ShimmerItemModel() as FeedItem } as ArrayList
        feedItems.postValue(shimmers)
        loadAds(20)
    }

    private fun loadAds(count: Int) {

        var completed = 0

        for (i in 0 until count) {
            loadAd {
                completed += 1
                if (it != null) {
                    mLoadedAds.add(FeedAdModel(it))
                }
                if(completed == count){
                    createDataSource()
                    combineItems()
                }
            }
        }
    }

}