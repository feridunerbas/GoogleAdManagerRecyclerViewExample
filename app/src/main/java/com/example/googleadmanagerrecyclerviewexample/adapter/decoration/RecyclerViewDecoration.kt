package com.example.googleadmanagerrecyclerviewexample.adapter.decoration

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.googleadmanagerrecyclerviewexample.extension.toPixels

class RecyclerViewDecoration(private val context: Context): RecyclerView.ItemDecoration() {

    private val spacing: Float = 5f

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                                state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val defaultMargin = spacing.toPixels(context).toInt()
        outRect.set(defaultMargin, defaultMargin, defaultMargin, defaultMargin)

    }

}