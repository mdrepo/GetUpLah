package com.mdrepo.networksdk

import retrofit2.http.GET

/**
 * Created by Mayur on 23/10/17.
 */

interface TransitStopService {

    @GET("maps/api/place/nearbysearch/json?key={apiKey}&location={lat},{long}&rankby=distance&types={type}")
    fun getStopsAround(apiKey: String, lat: String, long: String, type: String)
}