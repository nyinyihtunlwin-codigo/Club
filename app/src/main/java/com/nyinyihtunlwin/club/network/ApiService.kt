package com.nyinyihtunlwin.club.network

import com.nyinyihtunlwin.club.data.vos.CompanyVo
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("api/json/get/Vk-LhK44U")
    fun getClubData(): Observable<List<CompanyVo>>
}