package com.nyinyihtunlwin.club.utils

import android.content.Context
import android.content.SharedPreferences

class ConfigUtils(context: Context) {

    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE)

    companion object {
        val KEY_COM_SORT_ORDER = "KEY_COM_SORT_ORDER"

        private var INSTANCE: ConfigUtils? = null

        fun getInstance(): ConfigUtils {
            if (INSTANCE == null) {
                throw RuntimeException("ItemListModel is being invoked before initializing.")
            }
            val i = INSTANCE
            return i!!
        }

        fun initConfigUtils(context: Context) {
            INSTANCE = ConfigUtils(context)
        }
    }

    fun saveComSortOrder(accessToken: String) {
        mSharedPreferences.edit().putString(KEY_COM_SORT_ORDER, accessToken).apply()
    }

    fun loadComSortOrder(): String {
        return mSharedPreferences.getString(KEY_COM_SORT_ORDER, "")
    }

}