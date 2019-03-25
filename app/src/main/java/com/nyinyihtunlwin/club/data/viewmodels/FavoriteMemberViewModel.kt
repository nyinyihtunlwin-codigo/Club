package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.utils.AppUtils

class FavoriteMemberViewModel : BaseViewModel() {

    lateinit var mResponseLd: MutableLiveData<List<MemberVo>>

    init {
        super.initViewModel()
        mResponseLd = MutableLiveData()
    }

    fun onGetFavoriteMembers() {
        mResponseLd.value = ClubModel.getInstance().getFavoriteMembers()
    }

    fun onTapFavorite(memberId: String) {
        ClubModel.getInstance().favoriteMember(memberId)
    }
}