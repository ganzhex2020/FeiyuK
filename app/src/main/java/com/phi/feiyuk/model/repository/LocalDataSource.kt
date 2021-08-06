package com.phi.feiyuk.model.repository

import androidx.lifecycle.LiveData
import com.phi.feiyuk.MyApp
import com.phi.feiyuk.db.AppDatabase
import com.phi.feiyuk.model.entity.UserInfoEntity
import kotlinx.coroutines.flow.Flow


/**
 *Author:ganzhe
 *时间:2020/12/28 12:39
 *描述:This is LocalDataSource
 */
class LocalDataSource {

    private val appDatabase = AppDatabase.getInstance(MyApp.CONTEXT)

    suspend fun insertUserInfo(userInfoEntity: UserInfoEntity){
        appDatabase.userDao().insertUserInfo(userInfoEntity)
    }


    fun getUserInfo(): Flow<List<UserInfoEntity>> {
        return appDatabase.userDao().getUserInfoLiveData()
    }

    suspend fun deleteAllUserInfo(){
        appDatabase.userDao().deleteAll()
    }



}