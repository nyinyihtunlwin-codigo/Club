package com.nyinyihtunlwin.club.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.utils.AppConstants

@Dao
abstract interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCompany(company: CompanyVo): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertCompanies(companies: List<CompanyVo>): LongArray

    @Query("SELECT * FROM ${AppConstants.TABLE_COMPANY}")
    abstract fun getCompanies(): List<CompanyVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_COMPANY} ORDER BY company DESC")
    abstract fun getCompaniesDescOrder(): List<CompanyVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_COMPANY} ORDER BY company")
    abstract fun getCompaniesAscOrder(): List<CompanyVo>

    @Query("SELECT * FROM ${AppConstants.TABLE_COMPANY} WHERE companyId = :id")
    abstract fun getCompanyById(id: String): CompanyVo?

    @Query("DELETE FROM ${AppConstants.TABLE_COMPANY} WHERE companyId = :id")
    abstract fun deleteCompanyById(id: String): Int

    @Query("SELECT * FROM ${AppConstants.TABLE_COMPANY} WHERE company = :keywords")
    abstract fun getCompanyByName(keywords:String): List<CompanyVo>?
}