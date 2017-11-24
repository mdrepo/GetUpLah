package com.mdrepo.getuplah

import android.content.Context
import android.content.pm.PackageManager
import android.databinding.ObservableBoolean
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.location.LocationManager
import android.content.Context.LOCATION_SERVICE
import android.util.Log


abstract class LocationActivity : AppCompatActivity(), PermissionUtil.PermissionListener, LocationPermissionDialog.Listener {

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mPermissionUtil: PermissionUtil? = null

    companion object {
        private val LOCATION_REQUEST: Int = 123
    }

    val permission = android.Manifest.permission.ACCESS_FINE_LOCATION

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mPermissionUtil = PermissionUtil()
        mPermissionUtil?.checkPermission(this, permission)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionGranted()
                } else {
                    onPermissionPreviouslyDenied()
                }
            }
        }
    }

    abstract fun onLocationFetched(location: Location)
    abstract fun onFetchingLocation()

    @SuppressWarnings("MissingPermission")
    private fun getLocation() {
        var gpsEnabled = false
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {

        }

        var networkGpsEnabled = false
        try {
            networkGpsEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
        }

        Log.d("TAG", "${networkGpsEnabled} ${gpsEnabled}")
        mFusedLocationClient?.lastLocation?.addOnCompleteListener(this) { task ->
                    // isFetchingLocation = false
                    Log.d("TAG", "exception=${task.exception} \n isSuccesful=${task.isSuccessful}  \n" +
                            " Result=${task.result} \n task=${task}")
                    if (task.isSuccessful && task.result != null) {
                        val location: Location = task.result
                        onLocationFetched(location)
                        Toast.makeText(applicationContext,
                                "lat=${location.latitude} long=${location.longitude}",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext,
                                "location failed",
                                Toast.LENGTH_SHORT).show()
                        task.exception?.printStackTrace()
                    }}
    }

    // Permission util callbacks
    override fun onNeedPermission() {
        mPermissionUtil?.requestPermission(this, LOCATION_REQUEST, permission)
    }

    override fun onPermissionPreviouslyDenied() {
        val locationPermissionDialog = LocationPermissionDialog.newInstance(
                getString(R.string.permission_needed),
                getString(R.string.location_permission_rationale))
        locationPermissionDialog.show(fragmentManager, "location")
    }

    override fun onPermissionDisabled() {
        Toast.makeText(this, "Location permission is denied", Toast.LENGTH_SHORT)
    }

    override fun onPermissionGranted() {
        getLocation()
        onFetchingLocation()
    }

    // Location dialog callback
    override fun onClicked(status: Boolean) {
        if (status)
            mPermissionUtil?.requestPermission(this, LOCATION_REQUEST, permission)
        else {
            onPermissionDisabled()
        }
    }
}
