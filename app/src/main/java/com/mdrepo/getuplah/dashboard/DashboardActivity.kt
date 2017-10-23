package com.mdrepo.getuplah.dashboard

import android.location.Location
import android.os.Bundle
import android.view.View
import com.mdrepo.getuplah.LocationActivity
import com.mdrepo.getuplah.R
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : LocationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_choose_location.setOnClickListener(View.OnClickListener {chooseLocation(it)})
    }

    private fun chooseLocation(ed: View) {

    }

    override fun onLocation(location: Location) {

    }
}

