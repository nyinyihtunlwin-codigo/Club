package com.nyinyihtunlwin.club.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.activities.MembersActivity
import com.nyinyihtunlwin.club.adapters.CompanyAdapter
import com.nyinyihtunlwin.club.data.viewmodels.CompanyViewModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.delegates.CompanyDelegate
import com.nyinyihtunlwin.club.events.CompanyFilterEvent
import kotlinx.android.synthetic.main.fragment_companies.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class CompaniesFragment : BaseFragment()
    , CompanyDelegate {

    private lateinit var mViewModel: CompanyViewModel
    private lateinit var mAdapter: CompanyAdapter

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

        rvCompanies.setEmptyView(vpEmpty)
        mAdapter = CompanyAdapter(context!!,this)
        rvCompanies.adapter = mAdapter
        rvCompanies.layoutManager = LinearLayoutManager(context)

        loadClubData()

        swipeRefresh.setOnRefreshListener {
            loadClubData()
        }

        mViewModel.mResponseLd.observe(this, Observer {
            dismissLoading()
            mAdapter.setNewData(it as MutableList<CompanyVo>)
        })
        mViewModel.mErrorLD.observe(this, Observer {
            dismissLoading()
            showPromptDialog(it.toString())
        })
    }

    private fun loadClubData() {
        showLoading()
        mViewModel.onGetClubData()
    }

    private fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    private fun dismissLoading() {
        swipeRefresh.isRefreshing = false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCompanyFilterEvent(event: CompanyFilterEvent) {
        loadClubData()
    }

    override fun onTapCompany(companyId: String) {
        startActivity(MembersActivity.newInstance(context!!, companyId))
    }

    override fun onTapCompanyWebsite(webUrl: String) {
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}
