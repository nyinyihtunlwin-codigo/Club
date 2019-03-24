package com.nyinyihtunlwin.club

import android.app.Application
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.utils.AppUtils
import com.nyinyihtunlwin.club.utils.ConfigUtils

class ClubApp:Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtils.initAppUtils(applicationContext)
        ConfigUtils.initConfigUtils(applicationContext)
        ClubModel.getInstance().initDatabase(applicationContext)
    }
}