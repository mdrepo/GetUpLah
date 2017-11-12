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


    val transitAPIService by lazy {
        TransitStopAPI.create()
    }

    public fun getStops(lat: String, long: String): Observable<PlacesResponse> {
        return Observable.create<PlacesResponse> { e ->
            transitAPIService
                    .getStopsAround("AIzaSyBfd8ukTjKUxAzrTZV6-W6HjzcnVGfEZT8",
                            lat + "," + long,
                            "transit_station")
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
                        Log.d("t", error.message)
                        e.onError(error)
                    })
        }
    }
}