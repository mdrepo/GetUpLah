package com.mdrepo.getuplah.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.location.Location
import android.os.Bundle
import android.view.View
import com.mdrepo.getuplah.LocationActivity
import com.mdrepo.getuplah.LocationViewModel
import com.mdrepo.getuplah.R
import com.mdrepo.getuplah.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : LocationActivity() {

    /**
     * https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyBfd8ukTjKUxAzrTZV6-W6HjzcnVGfEZT8&location=1.301800,103.837797&rankby=distance&language=zh-TW&types=bus_station

     */
    val locationViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_choose_location.setOnClickListener({chooseLocation(it)})
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewmodel = locationViewModel
        locationViewModel.location.observe(this, Observer { locationViewModel.loadTravelStops(it) })
    }

    private fun chooseLocation(ed: View) {

    }

    override fun onLocationFetched(location: Location) {
        locationViewModel?.location.value = location
    }

    override fun onFetchingLocation() {
        locationViewModel?.isLoading.set(true)
    }

    fun getViewModel(): LocationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
}

