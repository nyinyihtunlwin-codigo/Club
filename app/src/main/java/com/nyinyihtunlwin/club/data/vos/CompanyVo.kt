package com.nyinyihtunlwin.club.data.vos

import com.google.gson.annotations.SerializedName

class CompanyVo(
    @SerializedName("_id") var companyId: String = "",
    @SerializedName("company") var company: String = "",
    @SerializedName("website") var website: String = "",
    @SerializedName("logo") var logo: String = "",
    @SerializedName("about") var about: String = "",
    @SerializedName("members") var members: List<MemberVo>
)