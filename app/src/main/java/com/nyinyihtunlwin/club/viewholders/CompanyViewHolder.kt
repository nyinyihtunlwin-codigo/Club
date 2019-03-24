package com.nyinyihtunlwin.club.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.delegates.CompanyDelegate
import kotlinx.android.synthetic.main.view_item_company.view.*

class CompanyViewHolder(itemView: View, var delegate: CompanyDelegate) : BaseViewHolder<CompanyVo>(itemView) {
    override fun setData(data: CompanyVo) {
        mData = data

        Glide.with(itemView).load(mData.logo).into(itemView.ivCompany)

        itemView.tvCompanyName.text = mData.company
        itemView.tvCompanyAbout.text = mData.about

        itemView.btnGoToWeb.setOnClickListener {
            delegate.onTapCompanyWebsite(mData.website)
        }
    }

    override fun onClick(v: View?) {
        delegate.onTapCompany(mData.companyId)
    }
}