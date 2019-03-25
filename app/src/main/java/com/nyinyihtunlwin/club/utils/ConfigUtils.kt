package com.nyinyihtunlwin.club.utils

import android.content.Context
import android.content.SharedPreferences

class ConfigUtils(context: Context) {

    private var mSharedPreferences: SharedPreferences =
        context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE)

    companion object {
        val KEY_COM_SORT_ORDER = "KEY_COM_SORT_ORDER"
        val KEY_MEM_SORT_ORDER = "KEY_MEM_SORT_ORDER"
        val KEY_MEM_SORT_BY = "KEY_MEM_SORT_BY"

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

    fun saveComSortOrder(order: String) {
        mSharedPreferences.edit().putString(KEY_COM_SORT_ORDER, order).apply()
    }

    fun loadComSortOrder(): String {
        return mSharedPreferences.getString(KEY_COM_SORT_ORDER, "")
    }

    fun saveMemSortOrder(order: String) {
        mSharedPreferences.edit().putString(KEY_MEM_SORT_ORDER, order).apply()
    }

    fun loadMemSortOrder(): String {
        return mSharedPreferences.getString(KEY_MEM_SORT_ORDER, "")
    }

    fun saveMemSortBy(soryBy: Int) {
        mSharedPreferences.edit().putInt(KEY_MEM_SORT_BY, soryBy).apply()
    }

    fun loadMemSortBy(): Int {
        return mSharedPreferences.getInt(KEY_MEM_SORT_BY, 0)
    }

}