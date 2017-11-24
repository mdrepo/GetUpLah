package com.mdrepo.getuplah.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.mdrepo.getuplah.LocationActivity
import com.mdrepo.getuplah.LocationViewModel
import com.mdrepo.getuplah.R
import com.mdrepo.getuplah.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class DashboardActivity : LocationActivity() {

    private lateinit var binding: ActivityMainBinding
    /**
     * https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyBfd8ukTjKUxAzrTZV6-W6HjzcnVGfEZT8&location=1.301800,103.837797&rankby=distance&language=zh-TW&types=bus_station
     */
    val locationViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ed_choose_location.setOnClickListener({ chooseLocation(it) })
        binding.viewmodel = locationViewModel
        locationViewModel.location.observe(this, Observer { locationViewModel.loadTravelStops(it) })
        setupListAdapter()
    }

    private fun chooseLocation(ed: View) {

    }

    private fun setupListAdapter() {
        rc_transit_stops.layoutManager = LinearLayoutManager(applicationContext)
        val listAdapter = TransitStopAdapter(ArrayList(0))
        binding.rcTransitStops.adapter = listAdapter
    }

    override fun onLocationFetched(location: Location) {
        locationViewModel?.location.value = location
    }

    override fun onFetchingLocation() {
        locationViewModel?.isLoading.set(true)
    }

    fun getViewModel(): LocationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
}

