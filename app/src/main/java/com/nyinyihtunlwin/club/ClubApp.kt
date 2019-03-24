package com.nyinyihtunlwin.club

import android.app.Application
import com.nyinyihtunlwin.club.data.models.ClubModel

class ClubApp:Application() {
    override fun onCreate() {
        super.onCreate()
        ClubModel.getInstance().initDatabase(applicationContext)
    }
}