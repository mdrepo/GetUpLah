package com.mdrepo.getuplah

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
