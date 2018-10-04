package com.mdrepo.getuplah.dashboard

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.mdrepo.getuplah.LocationActivity
import com.mdrepo.getuplah.LocationViewModel
import com.mdrepo.getuplah.R
import com.mdrepo.getuplah.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class DashboardActivity : LocationActivity() {

    private lateinit var binding: ActivityMainBinding
    private val locationViewModel by lazy {
        getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ed_choose_location.setOnClickListener { chooseLocation(it) }
        binding.viewmodel = locationViewModel
        locationViewModel.location.observe(this, Observer {
            it?.takeIf {
                it.longitude != null && it.latitude != null
            }?.let {
                val latLng = applicationContext.getString(R.string.latlng, it.latitude, it.longitude)
                locationViewModel.loadTravelStops(latLng)
            }
        })
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
        locationViewModel.location.value = location
    }

    override fun onFetchingLocation() {
        locationViewModel.isLoading.set(true)
    }

    private fun getViewModel(): LocationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
}

