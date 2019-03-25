package com.nyinyihtunlwin.club.data.vos

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.nyinyihtunlwin.club.utils.AppConstants


@Entity(
    tableName = AppConstants.TABLE_MEMBER
)
class MemberVo {
    @PrimaryKey
    @SerializedName("_id")
    var memberId: String = ""

    @ColumnInfo(name = "companyId")
    var companyId:String = ""

    @SerializedName("age")
    var age: Int = 0

    @SerializedName("name")
    var name: NameVo? = null

    @SerializedName("email")
    var email: String = ""

    @SerializedName("phone")
    var phone: String = ""
}