package com.nyinyihtunlwin.club.fragments


import android.content.Intent
import android.net.Uri
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
import com.nyinyihtunlwin.club.data.viewmodels.FavoriteCompanyViewModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.delegates.CompanyDelegate
import kotlinx.android.synthetic.main.fragment_favorite_companies.view.*

class FavoriteCompaniesFragment : BaseFragment(),
    CompanyDelegate {

    private lateinit var mViewModel: FavoriteCompanyViewModel
    private lateinit var mAdapter: CompanyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_companies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProviders.of(this@FavoriteCompaniesFragment)
            .get(FavoriteCompanyViewModel::class.java)

        view.rvCompanies.setEmptyView(view.vpEmpty)
        mAdapter = CompanyAdapter(context!!, this)
        view.rvCompanies.adapter = mAdapter
        view.rvCompanies.layoutManager = LinearLayoutManager(context)

        mViewModel.onGetFavoriteCompanies()

        mViewModel.mResponseLd.observe(this, Observer {
            mAdapter.setNewData(it as MutableList<CompanyVo>)
        })
        mViewModel.mErrorLD.observe(this, Observer {
            showPromptDialog(it.toString())
        })
    }

    override fun onTapCompany(companyId: String) {
        startActivity(MembersActivity.newInstance(context!!, companyId))
    }

    override fun onTapCompanyWebsite(webUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(webUrl)
        if(activity!=null){
            activity?.startActivity(intent)
        }
    }

    override fun onTapFavoriteCompany(companyId: String) {
        mViewModel.onTapFavorite(companyId)
        mViewModel.onGetFavoriteCompanies()
    }

}
