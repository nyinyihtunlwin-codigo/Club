package com.nyinyihtunlwin.club.data.models

import androidx.lifecycle.MutableLiveData
import com.nyinyihtunlwin.club.data.vos.CompanyVo
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
                            responseLd.value = response
                        } else {
                            errorLd.value = "No Data"
                        }
                    }

                    override fun onError(p0: Throwable) {
                        errorLd.value = p0.localizedMessage
                    }
                })
    }
}