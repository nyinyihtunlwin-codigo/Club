package com.nyinyihtunlwin.club.viewholders

import android.view.View
import com.bumptech.glide.Glide
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.delegates.CompanyDelegate
import kotlinx.android.synthetic.main.view_item_company.view.*

class CompanyViewHolder(itemView: View, var delegate: CompanyDelegate) : BaseViewHolder<CompanyVo>(itemView) {


    override fun setData(data: CompanyVo) {
        mData = data

        Glide.with(itemView).load(mData.logo).into(itemView.ivCompany)

        itemView.tvCompanyName.text = mData.company
        itemView.tvCompanyAbout.text = mData.about

        checkIsFavorite()

        itemView.ivFavorite.setOnClickListener {
            delegate.onTapFavoriteCompany(mData.companyId)
            checkIsFavorite()
        }
        itemView.btnGoToWeb.setOnClickListener {
            if(!mData.website.contains("https://")){
                delegate.onTapCompanyWebsite("https://${mData.website}")
            }else{
                delegate.onTapCompanyWebsite(mData.website)
            }
        }
    }

    private fun checkIsFavorite() {
        if (ClubModel.getInstance().isFavoriteCompany(mData.companyId)) {
            itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_24dp)
        } else {
            itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_border_24dp)
        }
    }

    override fun onClick(v: View?) {
        delegate.onTapCompany(mData.companyId)
    }
}