package com.nyinyihtunlwin.club.persistence.typeconverters

import androidx.room.TypeConverter
import com.nyinyihtunlwin.club.data.vos.NameVo

class NameTypeConverter {
    @TypeConverter
    fun fromFullName(value: String?): NameVo? {
        return value?.let { NameVo(it.split(" ")[0], it.split("")[1]) }
    }

    @TypeConverter
    fun nameToFullName(nameVo: NameVo?): String? {
        return nameVo?.first + " " + nameVo?.last
    }
}