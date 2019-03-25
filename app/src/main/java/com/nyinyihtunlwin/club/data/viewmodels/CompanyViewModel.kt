package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.utils.AppUtils

class CompanyViewModel : BaseViewModel() {

    lateinit var mResponseLd: MutableLiveData<List<CompanyVo>>

    init {
        super.initViewModel()
        mResponseLd = MutableLiveData()
    }

    fun onGetClubData() {
        if (ClubModel.getInstance().getCompanies().isEmpty()) {
            if (AppUtils.getInstance().hasConnection()) {
                ClubModel.getInstance().getClubData(mResponseLd, mErrorLD)
            } else {
                mErrorLD.value = "No internet connection!"
            }
        } else {
            mResponseLd.value = ClubModel.getInstance().getCompanies()
        }
    }
}