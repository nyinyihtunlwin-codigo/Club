package com.nyinyihtunlwin.club.viewholders

import android.view.View
import com.nyinyihtunlwin.club.R
import com.nyinyihtunlwin.club.data.models.ClubModel
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.delegates.MemberDelegate
import kotlinx.android.synthetic.main.view_item_member.view.*

class MemberViewHolder(itemView: View,var delegate: MemberDelegate): BaseViewHolder<MemberVo>(itemView) {
    override fun setData(data: MemberVo) {
        mData = data
        val name = "${mData.name!!.first} ${mData.name!!.last}"
        itemView.tvName.text = name
        itemView.tvAge.text = mData.age.toString()
        itemView.tvEmail.text = mData.email
        itemView.tvPhone.text = mData.phone

        checkIsFavorite()

        itemView.ivFavorite.setOnClickListener {
            delegate.onTapFavoriteMember(mData.memberId)
            checkIsFavorite()
        }
    }

    private fun checkIsFavorite() {
        if (ClubModel.getInstance().isFavoriteMember(mData.memberId)) {
            itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_24dp)
        } else {
            itemView.ivFavorite.setImageResource(R.drawable.ic_favorite_border_24dp)
        }
    }

    override fun onClick(v: View?) {
    }
}