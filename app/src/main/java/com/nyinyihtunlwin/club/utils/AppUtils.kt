package com.nyinyihtunlwin.club.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

class AppUtils private constructor(var context: Context) {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: AppUtils? = null

        fun getInstance(): AppUtils {
            if (INSTANCE == null) {
                throw RuntimeException("AppUtils is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initAppUtils(context: Context) {
            INSTANCE = AppUtils(context)
        }
    }

    fun hasConnection(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}