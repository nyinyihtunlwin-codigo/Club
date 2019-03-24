package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.MemberVo

class MemberViewModel : BaseViewModel() {

    lateinit var mResponseLd: MutableLiveData<List<MemberVo>>

    init {
        super.initPresenter()
        mResponseLd = MutableLiveData()
    }

    fun onGetMembers(companyId:String) {
        if (!ClubModel.getInstance().getCompanies().isEmpty()) {
            mResponseLd.value = ClubModel.getInstance().getMembersByCompany(companyId)
        }else{
            mErrorLD.value = "No data!"
        }
    }
}