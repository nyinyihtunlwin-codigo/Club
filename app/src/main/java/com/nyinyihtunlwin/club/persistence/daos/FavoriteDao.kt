package com.nyinyihtunlwin.club.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyinyihtunlwin.club.data.vos.FavoriteVo
import com.nyinyihtunlwin.club.utils.AppConstants

@Dao
abstract interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorite(favorite: FavoriteVo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertFavorites(favorites: List<FavoriteVo>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_FAVORITES} WHERE itemType = :itemType")
    abstract fun getFavorites(itemType:String): List<FavoriteVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_FAVORITES}")
    abstract fun getFavoriteCompanies(): List<FavoriteVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_FAVORITES}")
    abstract fun getFavoriteMembers(): List<FavoriteVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_FAVORITES} WHERE itemId = :id")
    abstract fun getFavoriteById(id: String): Int

    @Query("DELETE FROM ${AppConstants.TABLE_FAVORITES} WHERE itemId = :id")
    abstract fun deleteFavoriteById(id: String): Int

}