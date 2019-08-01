package com.example.googleadmanagerrecyclerviewexample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.googleadmanagerrecyclerviewexample.R
import com.example.googleadmanagerrecyclerviewexample.adapter.FeedItemsAdapter
import com.example.googleadmanagerrecyclerviewexample.adapter.decoration.RecyclerViewDecoration
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.model.FeedItem
import com.example.googleadmanagerrecyclerviewexample.model.FeedModel
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd

abstract class FeedActivity: AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var layoutManager: StaggeredGridLayoutManager? = null
    private var adapter: FeedItemsAdapter? = null
    protected var mLoadedAds = arrayListOf<FeedAdModel>()
    protected var mItems = arrayListOf<FeedModel>()

    protected val feedItems: MutableLiveData<ArrayList<FeedItem>> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setUpRecyclerView()
        feedItems.observe(this, Observer {
            adapter?.updateDataSource(it)

        })
    }

    fun loadAd(onLoaded: (ad: UnifiedNativeAd?) -> Unit){
        AdLoader.Builder(this, "/6499/example/native")
            .forUnifiedNativeAd { ad : UnifiedNativeAd ->
                onLoaded(ad)
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    onLoaded(null)
                }
            })
            .withNativeAdOptions(
                NativeAdOptions.Builder()
                    .build())
            .build()
            .loadAd(
                PublisherAdRequest.Builder()
                    .addTestDevice("4D94CB9811063D8059D508BFE00651B4")
                    .addTestDevice("1B8593421101D9D5140B06EA675F7325")
                    .addTestDevice("7A9A2CF2181648F5B1A8A850B805CF0A").build())
    }



    private fun setUpRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter = FeedItemsAdapter()

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            RecyclerViewDecoration(
                this
            )
        )
    }



    protected fun combineItems(){
        val dataSource = arrayListOf<FeedItem>()
        dataSource.addAll(mItems)
        for (i in 0 until mLoadedAds.size){
            dataSource.add(i * 10 + 5, mLoadedAds[i])
        }
        feedItems.postValue(dataSource)
    }

    protected fun createDataSource(){
        for(i in 0 until 200){
            mItems.add(FeedModel.random())
        }
    }

}
