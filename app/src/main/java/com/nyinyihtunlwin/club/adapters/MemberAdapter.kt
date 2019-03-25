package com.nyinyihtunlwin.club.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.delegates.MemberDelegate
import com.nyinyihtunlwin.club.viewholders.BaseViewHolder
import com.nyinyihtunlwin.club.viewholders.MemberViewHolder

class MemberAdapter(context:Context,
                    var delegate:MemberDelegate): BaseRecyclerAdapter<MemberViewHolder, MemberVo>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MemberVo> {
        val view = mLayoutInflator.inflate(R.layout.view_item_member,parent,false)
        return MemberViewHolder(view,delegate)
    }
}