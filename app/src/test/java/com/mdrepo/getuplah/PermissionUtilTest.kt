package com.mdrepo.getuplah

import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import junit.framework.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by Mayur on 11/12/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class PermissionUtilTest {

    @Test
    fun testHasPermission() {
        `when`(ContextCompat.checkSelfPermission(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
                .thenReturn(PackageManager.PERMISSION_GRANTED)
        var permissionUtil = PermissionUtil()
        assertTrue(permissionUtil.hasPermission(ArgumentMatchers.any(), ArgumentMatchers.anyString()))
    }
}