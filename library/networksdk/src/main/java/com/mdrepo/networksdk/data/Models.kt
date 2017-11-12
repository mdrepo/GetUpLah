package com.mdrepo.networksdk.data

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by Mayur Dube on 12/11/17.
 */
public data class PlacesResponse(@SerializedName("next_page_token") val nextPageToken: String,
        // @SerializedName("html_attributions") val htmlAttributions: Objects,
                                 @SerializedName("results") val results: Array<Result>,
                                 @SerializedName("status") val status: String) {
    fun isOk(): Boolean = "OK" == status
}

public data class Geometry(@SerializedName("location") val location: Location)

public data class Location(@SerializedName("lat") val latitue: String, @SerializedName("lng") val longitude: String)

public data class Result(@SerializedName("geometry") val geometry: Geometry,
                         @SerializedName("icon") val iconURL: String,
        //@SerializedName("id") val id: String,
                         @SerializedName("name") val name: String,
        //@SerializedName("photos") val photoURLs: String,
                         @SerializedName("place_id") val placeId: String,
                         @SerializedName("rating") val rating: String,
                         @SerializedName("reference") val reference: String,
                         @SerializedName("types") val types: Array<String>,
                         @SerializedName("scope") val scope: String,
                         @SerializedName("vicinity") val vicinity: String)

