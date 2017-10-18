package com.mdrepo.getuplah

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


open class LocationActivity : AppCompatActivity(), PermissionUtil.PermissionListener, LocationPermissionDialog.Listener {
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mPermissionUtil: PermissionUtil? = null

    companion object {
        private val LOCATION_REQUEST: Int = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mPermissionUtil = PermissionUtil()
        mPermissionUtil?.checkPermission(this, android.Manifest.permission_group.LOCATION)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            LOCATION_REQUEST -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onPermissionGranted()
                } else {
                    onPermissionDisabled()
                }
            }
        }
    }

    override fun onNeedPermission() {
        mPermissionUtil?.requestPermission(this, LOCATION_REQUEST, android.Manifest.permission_group.LOCATION)
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
    }

    override fun onClicked(status: Boolean) {
        mPermissionUtil?.requestPermission(this, LOCATION_REQUEST, android.Manifest.permission_group.LOCATION)
    }

    public fun getLocation() {

    }
}
