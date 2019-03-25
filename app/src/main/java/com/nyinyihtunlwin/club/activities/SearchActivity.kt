package com.nyinyihtunlwin.club.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.adapters.CompanyAdapter
import com.nyinyihtunlwin.club.adapters.MemberAdapter
import com.nyinyihtunlwin.club.data.viewmodels.SearchViewModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.delegates.CompanyDelegate
import com.nyinyihtunlwin.club.delegates.MemberDelegate
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : BaseActivity(),
    CompanyDelegate,
    MemberDelegate {

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    private lateinit var mViewModel: SearchViewModel

    private lateinit var mComAdapter: CompanyAdapter
    private lateinit var mMemAdapter: MemberAdapter

    private lateinit var mCompanies: List<CompanyVo>
    private lateinit var mMembers: List<MemberVo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        mViewModel = ViewModelProviders.of(this@SearchActivity).get(SearchViewModel::class.java)

        mComAdapter = CompanyAdapter(applicationContext!!, this)
        rvCompanies.adapter = mComAdapter
        rvCompanies.layoutManager = LinearLayoutManager(applicationContext)

        mMemAdapter = MemberAdapter(applicationContext!!, this)
        rvMembers.adapter = mMemAdapter
        rvMembers.layoutManager = LinearLayoutManager(applicationContext)

        mCompanies = arrayListOf()
        mMembers = arrayListOf()

        mViewModel.onGetAllCompanies()
        mViewModel.onGetAllMembers()

        rGSearch.setOnCheckedChangeListener { group, checkedId ->
            run {
                when (checkedId) {
                    rbCompany.id -> {
                        rvMembers.visibility = View.GONE
                        rvCompanies.visibility = View.VISIBLE
                    }
                    rbMember.id -> {
                        rvCompanies.visibility = View.GONE
                        rvMembers.visibility = View.VISIBLE
                    }
                }
            }
        }

        etSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.e("SB", p0.toString().length.toString())
                searchCompany(p0.toString())
                searchMember(p0.toString())
            }
        })

        etSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideSoftKeyboard(applicationContext)
                    return true
                }
                return false
            }
        })

        mViewModel.mComResponseLd.observeForever {
            mCompanies = it
            mComAdapter.setNewData(it as MutableList<CompanyVo>)
        }

        mViewModel.mMemResponseLd.observeForever {
            mMembers = it
            mMemAdapter.setNewData(it as MutableList<MemberVo>)
        }


    }

    private fun searchMember(keywords: String) {
        val memData = arrayListOf<MemberVo>()
        for (mem in mMembers) {
            if (mem.name != null) {
                if (mem.name?.first!!.toLowerCase().contains(keywords.toLowerCase()) ||
                    mem.name?.last!!.toLowerCase().contains(keywords.toLowerCase())
                ) {
                    memData.add(mem)
                }
            }
        }
        mMemAdapter.setNewData(memData as MutableList<MemberVo>)
    }

    private fun searchCompany(keywords: String) {
        val comData = arrayListOf<CompanyVo>()
        for (com in mCompanies) {
            if (com.company.toLowerCase().contains(keywords.toLowerCase())) {
                comData.add(com)
            }
        }
        mComAdapter.setNewData(comData as MutableList<CompanyVo>)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onTapCompany(companyId: String) {
        startActivity(MembersActivity.newInstance(applicationContext, companyId))
    }

    override fun onTapCompanyWebsite(webUrl: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(webUrl)
        startActivity(intent)
    }

    override fun onTapFavoriteCompany(companyId: String) {
        mViewModel.onTapFavoriteCompany(companyId)
    }

    override fun onTapFavoriteMember(memberId: String) {
        mViewModel.onTapFavoriteMember(memberId)
    }

}
