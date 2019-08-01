package com.example.googleadmanagerrecyclerviewexample.adapter.viewHolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googleadmanagerrecyclerviewexample.model.FeedAdModel
import com.example.googleadmanagerrecyclerviewexample.R
import com.google.android.gms.ads.formats.MediaView
import com.google.android.gms.ads.formats.UnifiedNativeAdView

class AdViewHolder (private val parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(
    R.layout.layout_feed_ad_item,parent,false)) {
    private var mediaView: MediaView = itemView.findViewById(R.id.mediaView)
    var textViewBody: TextView = itemView.findViewById(R.id.textViewBody)
    var textViewCallToAction: TextView = itemView.findViewById(R.id.textViewCallToAction)
    val nativeAdView: UnifiedNativeAdView?
        get(){
            return itemView as? UnifiedNativeAdView
        }

    fun bind(model: FeedAdModel){

        textViewCallToAction.text = model.ad.callToAction
        textViewBody.text = model.ad.body

        nativeAdView?.mediaView = mediaView
        nativeAdView?.bodyView = textViewBody
        nativeAdView?.callToActionView = textViewCallToAction
        nativeAdView?.setNativeAd(model.ad)

    }
}