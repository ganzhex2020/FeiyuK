package com.phi.feiyuk.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.phi.feiyuk.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow


/**
 *Author:ganzhe
 *时间:2020/11/9 19:00
 *描述:This is UserInfoDao
 */

@Dao
interface UserInfoDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfoEntity: UserInfoEntity)

    @Query("SELECT * FROM UserInfoEntity")
    fun getUserInfo():List<UserInfoEntity>
//
    @Query("SELECT * FROM UserInfoEntity")
    fun getUserInfoLiveData():Flow<List<UserInfoEntity>>
//
    @Query("DELETE FROM UserInfoEntity")
    suspend fun deleteAll()



}