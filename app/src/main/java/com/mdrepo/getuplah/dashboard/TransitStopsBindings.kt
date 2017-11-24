package com.mdrepo.getuplah.dashboard

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.mdrepo.networksdk.data.Result

/**
 * Created by Mayur on 24/11/17.
 */
object TransitStopsBindings {

    @BindingAdapter("bind:items")
    @JvmStatic fun setItems(recyclerView: RecyclerView, items: List<Result>) {
        with(recyclerView.adapter as TransitStopAdapter) {
            replaceData(items)
        }
    }
}