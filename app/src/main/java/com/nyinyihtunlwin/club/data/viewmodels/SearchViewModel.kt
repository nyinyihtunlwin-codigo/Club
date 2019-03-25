package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.data.vos.MemberVo

class SearchViewModel : BaseViewModel() {

    lateinit var mComResponseLd: MutableLiveData<List<CompanyVo>>
    lateinit var mMemResponseLd: MutableLiveData<List<MemberVo>>

    init {
        super.initViewModel()
        mComResponseLd = MutableLiveData()
        mMemResponseLd = MutableLiveData()
    }

    fun onGetAllCompanies() {
        mComResponseLd.value = ClubModel.getInstance().getAllCompanies()
    }

    fun onGetAllMembers() {
        mMemResponseLd.value = ClubModel.getInstance().getAllMembers()
    }

    fun onTapFavoriteCompany(companyId: String) {
        ClubModel.getInstance().favoriteCompany(companyId)
    }

    fun onTapFavoriteMember(companyId: String) {
        ClubModel.getInstance().favoriteCompany(companyId)
    }
}