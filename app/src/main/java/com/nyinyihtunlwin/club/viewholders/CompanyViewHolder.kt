package com.nyinyihtunlwin.club.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import kotlinx.android.synthetic.main.view_item_company.view.*

class CompanyViewHolder(itemView: View) : BaseViewHolder<CompanyVo>(itemView) {
    override fun setData(data: CompanyVo) {
        mData = data

        Glide.with(itemView).load(mData.logo).into(itemView.ivCompany)

        itemView.tvCompanyName.text = mData.company
        itemView.tvCompanyAbout.text = mData.about
    }

    override fun onClick(v: View?) {
    }
}