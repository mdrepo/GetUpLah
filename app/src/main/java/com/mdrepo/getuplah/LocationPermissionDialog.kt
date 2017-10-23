package com.mdrepo.getuplah

import android.app.AlertDialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle

/**
 * Created by Mayur Dube on 17/10/17.
 * AIzaSyBfd8ukTjKUxAzrTZV6-W6HjzcnVGfEZT8
 */
public class LocationPermissionDialog: DialogFragment() {

    private var mListener: Listener? = null

    companion object {
        private val LOCATION_TITLE = "LOCATION_TITLE"
        private val LOCATION_TEXT = "LOCATION_TEXT"

        fun newInstance(title: String, text: String): LocationPermissionDialog {
            val args: Bundle = Bundle()
            args.putString(LOCATION_TITLE, title)
            args.putString(LOCATION_TEXT, text)
            val frag = LocationPermissionDialog()
            frag.arguments = args
            return frag
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context !is Listener)
            throw IllegalArgumentException("Activity should implement Listener interface")
        else
            mListener = context
    }

    public override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog? {
        val title: String = arguments[LOCATION_TITLE] as String
        val text: String = arguments[LOCATION_TEXT] as String

        return AlertDialog.Builder(activity)
                .setTitle(title)
                .setMessage(text)
                .setPositiveButton("Okay", { _, _ -> run { mListener?.onClicked(true) }})
                .create()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        mListener?.onClicked(false)
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        mListener?.onClicked(false)
    }

    internal interface Listener {
        fun onClicked(status: Boolean)
    }

}