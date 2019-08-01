package com.example.googleadmanagerrecyclerviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.doubleclick.PublisherAdRequest
import com.google.android.gms.ads.formats.NativeAdOptions
import com.google.android.gms.ads.formats.UnifiedNativeAd

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var layoutManager: StaggeredGridLayoutManager? = null
    private var adapter: FeedItemsAdapter? = null
    private var mLoadedAds = arrayListOf<FeedAdModel>()
    private var mItems = arrayListOf<FeedModel>()
    var start = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        createDataSource()
        combineItemsAndNotifyAdapter()
        start = System.currentTimeMillis()
        startLoadingAds(20)
    }

    private fun startLoadingAds(count: Int) {
        if (count <= 0){
            val duration = System.currentTimeMillis() - start
            Log.d("Loaded ads count: ", mLoadedAds.size.toString())
            Log.d("Duration: ", duration.toString())
            return
        }
        val opitons = NativeAdOptions.Builder()
        AdLoader.Builder(this, "/6499/example/native")
            .forUnifiedNativeAd { ad : UnifiedNativeAd ->
                mLoadedAds.add(FeedAdModel(ad))
                startLoadingAds(count - 1)
                combineItemsAndNotifyAdapter()
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(errorCode: Int) {
                    startLoadingAds(count - 1)
                }
            })
            .withNativeAdOptions(
                opitons
                .build())
            .build()
            .loadAd(PublisherAdRequest.Builder()
                .addTestDevice("4D94CB9811063D8059D508BFE00651B4")
                .addTestDevice("1B8593421101D9D5140B06EA675F7325")
                .addTestDevice("7A9A2CF2181648F5B1A8A850B805CF0A").build())
    }

    private fun setUpRecyclerView(){
        recyclerView = findViewById(R.id.recyclerView)
        layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        adapter =  FeedItemsAdapter()

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(RecyclerViewDecoration(this))
    }

    private fun createDataSource(){

        for(i in 0 until 200){
            mItems.add(FeedModel.random())
        }


    }

    private fun combineItemsAndNotifyAdapter(){
        val dataSource = arrayListOf<FeedItem>()
        dataSource.addAll(mItems)
        for (i in 0 until mLoadedAds.size){
            dataSource.add(i * 10 + 5, mLoadedAds[i])
        }
        adapter?.updateDataSource(dataSource)
    }

}
