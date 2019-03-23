package com.nyinyihtunlwin.club.data.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    lateinit var mErrorLD: MutableLiveData<String>

    open fun initPresenter() {
        mErrorLD = MutableLiveData()
    }
}