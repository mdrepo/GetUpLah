package com.mdrepo.networksdk

import com.mdrepo.networksdk.data.PlacesResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor



/**
 * Created by Mayur on 23/10/17.
 */

interface TransitStopAPI {

    companion object {
        fun create(): TransitStopAPI {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://maps.googleapis.com")
                    .client(client)
                    .build()

            return retrofit.create(TransitStopAPI::class.java)
        }
    }
    @GET("maps/api/place/nearbysearch/json?&rankby=distance")
    public fun getStopsAround(@Query("key") apiKey: String,
                              @Query("location") lat: String,
                              @Query("types") type: String): Observable<PlacesResponse>
}