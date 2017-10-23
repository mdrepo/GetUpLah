package com.mdrepo.getuplah

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.location.Location
import android.widget.Toast
import io.reactivex.Observable
import java.util.*
import kotlin.properties.ObservableProperty

/**
 * Created by Mayur Dube on 21/10/17.
 */
class LocationViewModel(
        context: Application
): AndroidViewModel(context) {

    var location: MutableLiveData<Location> = MutableLiveData()
    var isLoading = ObservableBoolean(false)
    private val context: Context = context.applicationContext

    fun loadTravelStops(location: Location?) {
        if (location != null) {
            Toast.makeText(context, "Loading bus stops for ${location?.latitude}", Toast.LENGTH_SHORT).show()
        }
    }

}