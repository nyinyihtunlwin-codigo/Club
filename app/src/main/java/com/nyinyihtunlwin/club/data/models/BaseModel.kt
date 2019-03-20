package com.nyinyihtunlwin.club.data.models

import com.google.gson.Gson
import com.nyinyihtunlwin.club.network.ApiService
import com.nyinyihtunlwin.club.persistence.ClubDb
import com.nyinyihtunlwin.club.utils.AppConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseModel {
    companion object {
        lateinit var mApi: ApiService
        lateinit var mDatabase: ClubDb
    }

    init {
        val okHttpClient = OkHttpClient().newBuilder().build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        mApi = retrofit.create(ApiService::class.java)
    }
}