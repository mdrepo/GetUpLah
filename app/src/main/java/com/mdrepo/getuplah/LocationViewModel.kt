package com.mdrepo.getuplah

import android.annotation.SuppressLint
import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.location.Location
import android.widget.Toast
import com.mdrepo.networksdk.TransitService
import com.mdrepo.networksdk.data.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Mayur Dube on 21/10/17.
 */
class LocationViewModel(context: Application) : AndroidViewModel(context) {

    var location: MutableLiveData<Location> = MutableLiveData()
    var isLoading = ObservableBoolean(false)
    val items: ObservableList<Result> = ObservableArrayList()

    private val transitService by lazy {
        TransitService()
    }

    @SuppressLint("CheckResult")
    fun loadTravelStops(location: String) {
        transitService.getStops(location)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ results ->
                if (results.isOk()) {
                    with(items) {
                        clear()
                        addAll(results.results)
                    }
                }
                isLoading.set(false)
                isLoading.notifyPropertyChanged(BR.viewmodel)
            }, {
                isLoading.set(false)
            })
    }


}