package com.nyinyihtunlwin.club.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.data.viewmodels.CompanyViewModel


class CompaniesFragment : BaseFragment() {

    private lateinit var mViewModel: CompanyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProviders.of(this@CompaniesFragment)
            .get(CompanyViewModel::class.java)

        mViewModel.onGetClubData()

        mViewModel.mResponseLd.observe(this, Observer {

        })
        mViewModel.mErrorLD.observe(this, Observer {

        })
    }
}
