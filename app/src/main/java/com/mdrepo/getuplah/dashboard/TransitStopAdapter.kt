package com.mdrepo.getuplah.dashboard

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mdrepo.getuplah.R
import com.mdrepo.networksdk.data.Result

/**
 * Created by Mayur on 24/11/17.
 */
class TransitStopAdapter(private var transitStops: List<Result>) : RecyclerView.Adapter<TransitStopAdapter.TransitStopViewHolder>() {

    override fun getItemCount(): Int {
        return transitStops.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TransitStopViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.row_transit_stop, parent, false)
        return TransitStopViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransitStopViewHolder?, position: Int) {
        holder?.bind(transitStops[position])
    }

    fun replaceData(transitStops: List<Result>) {
        this.transitStops = transitStops
        notifyDataSetChanged()
    }

    class TransitStopViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title_transit)
        val icon: ImageView = view.findViewById(R.id.icon_transit)

        fun bind(transitStop: Result?) {
            transitStop?.let {
                title.setText(transitStop.name);
                if (transitStop.isBusstop()) icon.setImageResource(R.drawable.bus)
                if (transitStop.isSubwaystation()) icon.setImageResource(R.drawable.train)
            }

        }

    }
}