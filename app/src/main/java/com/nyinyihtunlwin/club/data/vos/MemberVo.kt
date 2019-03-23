package com.nyinyihtunlwin.club.data.vos

import com.google.gson.annotations.SerializedName

class MemberVo(
    @SerializedName("_id") var memberId: String = "",
    @SerializedName("age") var age: Int = 0,
    @SerializedName("name") var name: NameVo? = null,
    @SerializedName("email") var email: String = "",
    @SerializedName("phone") var phone: String = ""
)