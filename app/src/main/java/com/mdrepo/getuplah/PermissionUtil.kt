package com.mdrepo.getuplah

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.support.v13.app.FragmentCompat
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity


/**
 * Created by Mayur Dube on 18/10/17.
 */
public class PermissionUtil() {

    fun shouldAskPermission(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }

    fun hasPermission(context: Context,permission: String): Boolean {
        var status = false
        if (shouldAskPermission()) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED) {
                status = true
            }
        } else status = true
        return status
    }

    fun checkPermission(context: Context, permission: String) {
        val listener: PermissionListener  = context as PermissionListener
        if (!hasPermission(context, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity, permission)) {
                listener?.onPermissionPreviouslyDenied()
            } else if (!SharedPreferencesUtil.isLocationPermissionAsked()) {
                listener?.onNeedPermission()
            } else {
                listener?.onPermissionDisabled()
            }
        } else {
            listener?.onPermissionGranted()
        }
    }

    fun requestPermission(o: Any, permissionId: Int, permission: String) {
        if (o is Fragment) {
            FragmentCompat.requestPermissions(o, arrayOf(permission), permissionId)
        } else if (o is Activity) {
            ActivityCompat.requestPermissions(o as AppCompatActivity, arrayOf(permission), permissionId)
        }
    }

    public interface PermissionListener {
        /*
        * Callback to ask permission
        * */
        fun onNeedPermission()

        /*
        * Callback on permission denied
        * */
        fun onPermissionPreviouslyDenied();

        /*
        * Callback on permission "Never show again" checked and denied
        * */
        fun onPermissionDisabled();

        /*
        * Callback on permission granted
        * */
        fun onPermissionGranted();
    }
}