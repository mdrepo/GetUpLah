package com.mdrepo.getuplah

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableBoolean
import android.location.Location
import android.widget.Toast
import com.mdrepo.networksdk.TransitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mayur Dube on 21/10/17.
 */
class LocationViewModel(context: Application) : AndroidViewModel(context) {

    var location: MutableLiveData<Location> = MutableLiveData()
    var isLoading = ObservableBoolean(false)
    private val context: Context = context.applicationContext

    val transitService by lazy {
        TransitService()
    }

    fun loadTravelStops(location: Location?) {
        if (location != null) {
            Toast.makeText(context, "Loading bus stops for ${location?.latitude}", Toast.LENGTH_SHORT).show()
            transitService.getStops(location.latitude.toString(), location.longitude.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ busStops ->
                        val name = busStops.results[0].name
                        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                        isLoading.set(false)
                        isLoading.notifyPropertyChanged(BR.viewmodel)
                    }, {
                        Toast.makeText(context, "Operation failed", Toast.LENGTH_SHORT).show()
                        isLoading.set(false)
                    })
        }
    }

}