package com.nyinyihtunlwin.club.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.adapters.MemberAdapter
import com.nyinyihtunlwin.club.data.viewmodels.FavoriteMemberViewModel
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.delegates.MemberDelegate
import kotlinx.android.synthetic.main.fragment_favorite_members.view.*

class FavoriteMembersFragment : BaseFragment(),
MemberDelegate{

    private lateinit var mViewModel: FavoriteMemberViewModel
    private lateinit var mAdapter: MemberAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_members, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mViewModel = ViewModelProviders.of(this@FavoriteMembersFragment)
            .get(FavoriteMemberViewModel::class.java)

        view.rvMembers.setEmptyView(view.vpEmpty)
        mAdapter = MemberAdapter(context!!, this)
        view.rvMembers.adapter = mAdapter
        view.rvMembers.layoutManager = LinearLayoutManager(context)

        mViewModel.onGetFavoriteMembers()
        view.swipeRefresh.setOnRefreshListener {
            mViewModel.onGetFavoriteMembers()
        }

        mViewModel.mResponseLd.observe(this, Observer {
            view.swipeRefresh.isRefreshing = false
            mAdapter.setNewData(it as MutableList<MemberVo>)
        })
        mViewModel.mErrorLD.observe(this, Observer {
            view.swipeRefresh.isRefreshing = false
            showPromptDialog(it.toString())
        })
    }

    override fun onTapFavoriteMember(memberId: String) {
        mViewModel.onTapFavorite(memberId)
        mViewModel.onGetFavoriteMembers()
    }

}
