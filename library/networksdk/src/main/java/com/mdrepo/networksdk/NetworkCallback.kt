package com.mdrepo.networksdk

/**
 * Created by Mayur on 24/10/17.
 */

interface NetworkCallback<T> {
    public fun onResponse(result: T)
    public fun onError(errorCode: Int, errorMessage: String)
}