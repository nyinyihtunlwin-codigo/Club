package com.nyinyihtunlwin.club.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nyinyihtunlwin.club.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_FAVORITES)
class FavoriteVo(var itemId:String,var itemType:String) {
    @PrimaryKey(autoGenerate = true)
    var favId:Int = 0
}