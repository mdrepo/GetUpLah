package com.mdrepo.getuplah.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.mdrepo.getuplah.R
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ed_choose_location.setOnClickListener(View.OnClickListener {chooseLocation(it)})
    }

    private fun chooseLocation(ed: View) {

    }
}

