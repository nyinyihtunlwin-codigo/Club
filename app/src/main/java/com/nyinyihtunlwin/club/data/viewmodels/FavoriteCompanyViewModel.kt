package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.utils.AppUtils

class FavoriteCompanyViewModel : BaseViewModel() {

    lateinit var mResponseLd: MutableLiveData<List<CompanyVo>>

    init {
        super.initViewModel()
        mResponseLd = MutableLiveData()
    }

    fun onGetFavoriteCompanies() {
        mResponseLd.value = ClubModel.getInstance().getFavoriteCompanies()
    }

    fun onTapFavorite(companyId: String) {
        ClubModel.getInstance().favoriteCompany(companyId)
    }
}