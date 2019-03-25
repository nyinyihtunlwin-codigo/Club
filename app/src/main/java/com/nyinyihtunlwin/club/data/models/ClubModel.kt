package com.nyinyihtunlwin.club.data.models

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.data.vos.FavoriteVo
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.persistence.ClubDb
import com.nyinyihtunlwin.club.utils.AppConstants
import com.nyinyihtunlwin.club.utils.ConfigUtils
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ClubModel : BaseModel() {

    companion object {
        private var INSTANCE: ClubModel? = null
        fun getInstance(): ClubModel {
            if (INSTANCE == null) {
                INSTANCE = ClubModel()
            }
            val i = INSTANCE
            return i!!
        }
    }

    fun initDatabase(context: Context) {
        mDatabase = ClubDb.getDatabase(context)
    }

    fun getClubData(
        responseLd: MutableLiveData<List<CompanyVo>>,
        errorLd: MutableLiveData<String>
    ) {
        mApi.getClubData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<List<CompanyVo>> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(p0: Disposable) {
                    }

                    override fun onNext(response: List<CompanyVo>) {
                        if (response.isNotEmpty()) {
                            saveToDb(response)
                            responseLd.value = getCompanies()
                        } else {
                            errorLd.value = "No Data"
                        }
                    }

                    override fun onError(p0: Throwable) {
                        errorLd.value = p0.localizedMessage
                    }
                })
    }

    private fun saveToDb(response: List<CompanyVo>) {
        mDatabase.companyDao().insertCompanies(response)
        for (res in response) {
            for (mem in res.members) {
                mem.companyId = res.companyId
            }
            mDatabase.memberDao().insertMembers(res.members)
        }
    }

    fun getCompanies(): List<CompanyVo> {
        val loadComSortOrder = ConfigUtils.getInstance().loadComSortOrder()
        return when (loadComSortOrder) {
            AppConstants.COMPANY_ORDER_DEFAULT -> {
                mDatabase.companyDao().getCompanies()
            }
            AppConstants.COMPANY_ORDER_ASC -> {
                mDatabase.companyDao().getCompaniesAscOrder()
            }
            AppConstants.COMPANY_ORDER_DESC -> {
                mDatabase.companyDao().getCompaniesDescOrder()
            }
            else -> {
                mDatabase.companyDao().getCompanies()
            }
        }
    }

    fun getMembersByCompany(companyId: String): List<MemberVo>? {
        val loadMemSortBy = ConfigUtils.getInstance().loadMemSortBy()
        val loadMemSortOrder = ConfigUtils.getInstance().loadMemSortOrder()

        return when (loadMemSortOrder) {
            AppConstants.MEMBER_ORDER_DEFAULT -> {
                mDatabase.memberDao().getMembersByCompany(companyId)
            }
            AppConstants.MEMBER_ORDER_ASC -> {
                if (loadMemSortBy == AppConstants.MEMBER_ORDERBY_NAME) {
                    mDatabase.memberDao().getMembersByCompanyAscByName(companyId)
                } else {
                    mDatabase.memberDao().getMembersByCompanyAscByAge(companyId)
                }
            }
            AppConstants.MEMBER_ORDER_DESC -> {
                if (loadMemSortBy == AppConstants.MEMBER_ORDERBY_NAME) {
                    mDatabase.memberDao().getMembersByCompanyDescByName(companyId)
                } else {
                    mDatabase.memberDao().getMembersByCompanyDescByAge(companyId)
                }
            }
            else -> {
                mDatabase.memberDao().getMembersByCompany(companyId)
            }
        }
    }

    fun searchCompaniesByName(keywords: String): List<CompanyVo>? {
        return mDatabase.companyDao().getCompanyByName(keywords)
    }

    fun searchMembersByName(keywords: String): List<MemberVo>? {
        return mDatabase.memberDao().getMemberByName(keywords)
    }

    fun getAllCompanies(): List<CompanyVo>? {
        return mDatabase.companyDao().getCompaniesAscOrder()
    }

    fun getAllMembers(): List<MemberVo>? {
        return mDatabase.memberDao().getMembersAscOrder()
    }

    fun favoriteCompany(companyId: String) {
        if (mDatabase.favoriteDao().getFavoriteById(companyId) == 0) {
            mDatabase.favoriteDao().insertFavorite(FavoriteVo(companyId, AppConstants.TYPE_COMPANY))
        } else {
            mDatabase.favoriteDao().deleteFavoriteById(companyId)
        }
    }

    fun getFavoriteCompanies(): List<CompanyVo>? {
        val companies: ArrayList<CompanyVo> = arrayListOf()
        val favoriteCompanies = mDatabase.favoriteDao().getFavoriteCompanies()
        if (favoriteCompanies.isNotEmpty()) {
            for (fav in favoriteCompanies) {
                if (mDatabase.companyDao().getCompanyById(fav.itemId) != null) {
                    val company = mDatabase.companyDao().getCompanyById(fav.itemId)
                    companies.add(company!!)
                }
            }
        }
        return companies
    }

    fun isFavoriteCompany(companyId: String): Boolean {
        if (mDatabase.favoriteDao().getFavoriteById(companyId) != 0) {
            return true
        }
        return false
    }

    fun favoriteMember(memberId: String) {
        if (mDatabase.favoriteDao().getFavoriteById(memberId) == 0) {
            mDatabase.favoriteDao().insertFavorite(FavoriteVo(memberId, AppConstants.TYPE_MEMBER))
        } else {
            mDatabase.favoriteDao().deleteFavoriteById(memberId)
        }
    }

    fun getFavoriteMembers(): List<MemberVo>? {
        val members: ArrayList<MemberVo> = arrayListOf()
        val favoriteMembers = mDatabase.favoriteDao().getFavoriteMembers()
        if (favoriteMembers.isNotEmpty()) {
            for (fav in favoriteMembers) {
                if (mDatabase.memberDao().getMemberById(fav.itemId) != null) {
                    val member = mDatabase.memberDao().getMemberById(fav.itemId)
                    members.add(member!!)
                }
            }
        }
        return members
    }

    fun isFavoriteMember(memberId: String): Boolean {
        if (mDatabase.favoriteDao().getFavoriteById(memberId) != 0) {
            return true
        }
        return false
    }
}