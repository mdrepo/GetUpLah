package com.mdrepo.networksdk

import com.mdrepo.networksdk.data.PlacesResponse
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Mayur on 23/10/17.
 */

interface TransitStopAPI {

    companion object {
        const val PARAM_KEY = "key"
        const val PARAM_LOCATION = "location"
        const val PARAM_TYPES = "types"

        fun create(): TransitStopAPI {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Config.BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(TransitStopAPI::class.java)
        }
    }
    @GET(Config.PATH_TO_MAPS)
    public fun getStopsAround(@Query(PARAM_KEY) apiKey: String,
                              @Query(PARAM_LOCATION) lat: String,
                              @Query(PARAM_TYPES) type: String): Observable<PlacesResponse>
}