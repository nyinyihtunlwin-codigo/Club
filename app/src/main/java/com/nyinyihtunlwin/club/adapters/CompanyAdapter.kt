package com.nyinyihtunlwin.club.adapters

import android.content.Context
import android.view.ViewGroup
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.viewholders.BaseViewHolder
import com.nyinyihtunlwin.club.viewholders.CompanyViewHolder

class CompanyAdapter(context:Context): BaseRecyclerAdapter<CompanyViewHolder, CompanyVo>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<CompanyVo> {
        val view = mLayoutInflator.inflate(R.layout.view_item_company,parent,false)
        return CompanyViewHolder(view)
    }
}