package com.mdrepo.getuplah

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


abstract class LocationActivity : AppCompatActivity(), PermissionUtil.PermissionListener, LocationPermissionDialog.Listener {

    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mPermissionUtil: PermissionUtil? = null
    var isFetchingLocation: Boolean = false

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

    abstract fun onLocation(location: Location)

    @SuppressWarnings("MissingPermission")
    private fun getLocation() {
        mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(this) { task ->
                    // isFetchingLocation = false
                    if (task.isSuccessful && task.result != null) {
                        val location: Location = task.result
                        Toast.makeText(applicationContext,
                                "lat=${location.latitude} long=${location.longitude}",
                                Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext,
                                "location failed with ${task.exception}",
                                Toast.LENGTH_SHORT).show()
                        task.exception!!.printStackTrace()
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
        isFetchingLocation = true
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
