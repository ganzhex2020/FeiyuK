package com.phi.feiyuk.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.phi.feiyuk.model.entity.UserInfoEntity


/**
 *Author:ganzhe
 *时间:2020/11/9 16:05
 *描述:This is AppDatabase
 */

@Database(entities = [UserInfoEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserInfoDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "feiyuk.db"
            )
                .build()
    }
}