package com.nyinyihtunlwin.club.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.adapters.MemberAdapter
import com.nyinyihtunlwin.club.data.viewmodels.MemberViewModel
import kotlinx.android.synthetic.main.activity_members.*
import kotlinx.android.synthetic.main.content_members.*

class MembersActivity : BaseActivity() {


    private lateinit var mViewModel: MemberViewModel
    private lateinit var mAdapter: MemberAdapter

    private var mCompanyId: String = ""

    companion object {
        const val KEY_COMPANY_ID = "KEY_COMPANY_ID"
        fun newInstance(
            context: Context
            , companyId: String
        ): Intent {
            val intent = Intent(context, MembersActivity::class.java)
            intent.putExtra(KEY_COMPANY_ID, companyId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_members)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        if (intent.getStringExtra(KEY_COMPANY_ID) != null) {
            mCompanyId = intent.getStringExtra(KEY_COMPANY_ID)
        }

        mViewModel = ViewModelProviders.of(this@MembersActivity)
            .get(MemberViewModel::class.java)

        rvMembers.setEmptyView(vpEmpty)
        mAdapter = MemberAdapter(applicationContext!!)
        rvMembers.adapter = mAdapter
        rvMembers.layoutManager = LinearLayoutManager(applicationContext)

        mViewModel.onGetMembers(mCompanyId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
