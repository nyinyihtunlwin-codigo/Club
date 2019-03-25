package com.nyinyihtunlwin.club.viewholders

import android.view.View
import com.nyinyihtunlwin.club.data.vos.MemberVo
import kotlinx.android.synthetic.main.view_item_member.view.*

class MemberViewHolder(itemView:View): BaseViewHolder<MemberVo>(itemView) {
    override fun setData(data: MemberVo) {
        mData = data
        val name = "${mData.name!!.first} ${mData.name!!.last}"
        itemView.tvName.text = name
        itemView.tvAge.text = mData.age.toString()
        itemView.tvEmail.text = mData.email
        itemView.tvPhone.text = mData.phone
    }

    override fun onClick(v: View?) {
    }
}