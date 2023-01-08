package com.enigmacamp.simple_news.utils

import android.app.Activity
import android.app.AlertDialog
import com.enigmacamp.simple_news.R


class Dialog(activity: Activity) {
    private var dialog: AlertDialog

    init {
        val builder = AlertDialog.Builder(activity, R.style.RadiusDialog)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading, null))
        builder.setCancelable(false)
        dialog = builder.create()
    }

    fun show() {
        dialog.show()
        dialog.window?.setLayout(600, 400)
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}