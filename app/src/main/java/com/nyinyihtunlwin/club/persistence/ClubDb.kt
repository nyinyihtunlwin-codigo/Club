package com.nyinyihtunlwin.club.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nyinyihtunlwin.club.data.vos.CompanyVo
import com.nyinyihtunlwin.club.data.vos.MemberVo
import com.nyinyihtunlwin.club.persistence.daos.CompanyDao
import com.nyinyihtunlwin.club.persistence.daos.MemberDao
import com.nyinyihtunlwin.club.persistence.typeconverters.NameTypeConverter

@Database(
    entities = [CompanyVo::class, MemberVo::class], version = 1, exportSchema = false
)
@TypeConverters(NameTypeConverter::class)
abstract class ClubDb : RoomDatabase() {

    abstract fun companyDao(): CompanyDao
    abstract fun memberDao(): MemberDao

    companion object {

        private const val DB_NAME = "club.db"
        private var INSTANCE: ClubDb? = null

        fun getDatabase(context: Context): ClubDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, ClubDb::class.java, DB_NAME)
                    .allowMainThreadQueries() //Remove this after testing. Access to DB should always be from background thread.
                    .build()
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}