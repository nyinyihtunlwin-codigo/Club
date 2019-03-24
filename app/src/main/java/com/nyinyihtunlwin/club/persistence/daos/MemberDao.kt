package com.nyinyihtunlwin.club.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.utils.AppConstants

@Dao
abstract interface MemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMember(member: MemberVo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMembers(members: List<MemberVo>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_MEMBER}")
    abstract fun getMembers(): List<MemberVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_MEMBER} ORDER BY :order DESC")
    abstract fun getMembersDescBy(order: String): List<MemberVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_MEMBER} ORDER BY :order DESC")
    abstract fun getMembersAesccBy(order: String): List<MemberVo>


    @Query("SELECT * FROM ${AppConstants.TABLE_MEMBER} WHERE companyId = :companyId")
    abstract fun getMembersByCompany(companyId: String): List<MemberVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_MEMBER} WHERE memberId = :id")
    abstract fun getMemberById(id: String): MemberVo?

    @Query("DELETE FROM ${AppConstants.TABLE_MEMBER} WHERE memberId = :id")
    abstract fun deleteMemberById(id: String): Int
}