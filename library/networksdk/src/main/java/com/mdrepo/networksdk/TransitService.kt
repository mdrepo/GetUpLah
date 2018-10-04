package com.mdrepo.networksdk

import android.util.Log
import com.mdrepo.networksdk.data.PlacesResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.io.IOException

/**
 * Created by Mayur Dube on 12/11/17.
 */
class TransitService {

    companion object {
        const val MAPS_QUERY_TYPE_TRANSIT_STATION = "transit_station"
    }

    private val transitAPIService by lazy {
        TransitStopAPI.create()
    }

    fun getStops(latlng: String): Observable<PlacesResponse> {
        return Observable.create<PlacesResponse> { e ->
            transitAPIService
                .getStopsAround(BuildConfig.MAPS_KEY,
                    latlng,
                    MAPS_QUERY_TYPE_TRANSIT_STATION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.isOk()) {
                        e.onNext(response)
                    } else {
                        e.onError(IOException())
                    }
                }, { error ->
                    error.printStackTrace()
                    e.onError(error)
                })
        }
    }
}