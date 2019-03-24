package com.nyinyihtunlwin.club.data.vos

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.club.utils.AppConstants

@Entity(
    tableName = AppConstants.TABLE_COMPANY
)
class CompanyVo {
    @PrimaryKey
    @SerializedName("_id")
    var companyId: String = ""
    @SerializedName("company")
    var company: String = ""
    @SerializedName("website")
    var website: String = ""
    @SerializedName("logo")
    var logo: String = ""
    @SerializedName("about")
    var about: String = ""
    @Ignore
    @SerializedName("members")
    var members: List<MemberVo> = ArrayList()
}
