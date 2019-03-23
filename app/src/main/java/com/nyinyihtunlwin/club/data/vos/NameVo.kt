package com.nyinyihtunlwin.club.data.vos

import com.google.gson.annotations.SerializedName

class NameVo(
    @SerializedName("first") var first: String,
    @SerializedName("last") var last: String
)