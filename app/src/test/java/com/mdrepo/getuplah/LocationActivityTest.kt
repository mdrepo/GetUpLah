package com.mdrepo.getuplah

import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config


/**
 * Created by Mayur on 20/10/17.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
public class LocationActivityTest {

    private lateinit var controller: ActivityController<LocationActivity>
    private lateinit var activity: LocationActivity
    private lateinit var mPermissionUtil: PermissionUtil

    @Before
    public fun setup() {
        controller = Robolectric.buildActivity(LocationActivity::class.java)
        mPermissionUtil = mock(PermissionUtil::class.java)

    }

    @Test
    public fun testPermissionUtilLocationDisabled() {
        activity = controller.get()
        `when`(mPermissionUtil!!.hasPermission(activity, activity.permission)).thenReturn(true)
        assertTrue(mPermissionUtil!!.hasPermission(activity, activity.permission))
        controller.create()
    }
}