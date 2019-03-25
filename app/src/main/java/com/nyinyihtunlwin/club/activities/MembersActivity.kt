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
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.delegates.MemberDelegate
import com.nyinyihtunlwin.club.dialogs.MemberFilterDialog
import com.nyinyihtunlwin.club.events.MemberFilterEvent
import com.nyinyihtunlwin.club.utils.AppConstants
import com.nyinyihtunlwin.club.utils.ConfigUtils
import kotlinx.android.synthetic.main.activity_members.*
import kotlinx.android.synthetic.main.content_members.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MembersActivity : BaseActivity(),
MemberDelegate{

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
        ConfigUtils.getInstance().saveMemSortBy(AppConstants.MEMBER_ORDERBY_NAME)
        ConfigUtils.getInstance().saveMemSortOrder(AppConstants.MEMBER_ORDER_DEFAULT)

        mViewModel = ViewModelProviders.of(this@MembersActivity)
            .get(MemberViewModel::class.java)

        rvMembers.setEmptyView(vpEmpty)
        mAdapter = MemberAdapter(applicationContext!!,this)
        rvMembers.adapter = mAdapter
        rvMembers.layoutManager = LinearLayoutManager(applicationContext)

        mViewModel.onGetMembers(mCompanyId)
        mViewModel.mResponseLd.observeForever {
            mAdapter.setNewData(it as MutableList<MemberVo>)
        }
        mViewModel.mErrorLD.observeForever {
            showPromptDialog(it)
        }

        ivFilter.setOnClickListener {
            val fragmentManager = supportFragmentManager
            val newFragment = MemberFilterDialog()
            newFragment.show(fragmentManager.beginTransaction(), "")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMemberFilterEvent(event: MemberFilterEvent) {
        mViewModel.onGetMembers(mCompanyId)
    }

    override fun onTapFavoriteMember(memberId: String) {
        mViewModel.onTapFavorite(memberId)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

}
