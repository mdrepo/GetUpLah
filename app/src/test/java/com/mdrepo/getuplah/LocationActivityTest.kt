package com.mdrepo.getuplah

import android.location.Location
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

    private lateinit var controller: ActivityController<TestActivity>
    private lateinit var activity: TestActivity
    private lateinit var mPermissionUtil: PermissionUtil

    @Before
    public fun setup() {
        controller = Robolectric.buildActivity(TestActivity::class.java)
        mPermissionUtil = mock(PermissionUtil::class.java)
        activity = controller.create().start().resume().get()
    }

    @Test
    public fun testPermissionUtilLocationDisabled() {
        `when`(mPermissionUtil.hasPermission(activity, activity.permission)).thenReturn(true)
        assertTrue(mPermissionUtil.hasPermission(activity, activity.permission))
    }

    class TestActivity: LocationActivity() {
        override fun onLocationFetched(location: Location) {

        }

        override fun onFetchingLocation() {

        }

    }
}