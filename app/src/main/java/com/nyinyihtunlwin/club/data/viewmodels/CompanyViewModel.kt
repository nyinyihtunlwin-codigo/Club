package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo

class CompanyViewModel:BaseViewModel() {

    lateinit var mResponseLd:MutableLiveData<List<CompanyVo>>

    init {
        super.initPresenter()
        mResponseLd = MutableLiveData()
    }

    fun onGetClubData(){
        ClubModel.getInstance().getClubData(mResponseLd,mErrorLD)
    }
}