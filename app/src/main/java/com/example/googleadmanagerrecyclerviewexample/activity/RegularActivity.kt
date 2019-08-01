package com.example.googleadmanagerrecyclerviewexample.activity

import android.os.Bundle
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.model.FeedModel

class RegularActivity: FeedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDataSource()
        combineItems()
        loadAds(20)
    }

    private fun loadAds(count: Int) {
        if (count <= 0){
            return
        }
        loadAd {
            if (it != null){
                mLoadedAds.add(FeedAdModel(it))
                combineItems()
                loadAds(count -1)
            }
        }

    }

}
