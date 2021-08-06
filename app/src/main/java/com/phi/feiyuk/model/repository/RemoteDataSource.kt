package com.phi.feiyuk.model.repository

import com.phi.feiyuk.im.utils.JPushUtil
import com.phi.feiyuk.model.api.ServiceApi
import com.phi.feiyuk.model.entity.*
import com.phi.httplib.model.BaseResult
import com.phi.httplib.model.InfoTBean

/**
 *Author:ganzhe
 *时间:2020/10/23 22:43
 *描述:This is RemoteDataSource
 */
class RemoteDataSource(private val serviceApi: ServiceApi) {

    //获取通用配置数据
    suspend fun getConfig(): BaseResult<List<ConfigEntity>> {
        return  serviceApi.getConfig()
    }

    //获取首页直播数据
    suspend fun getHomeLiveData(pageIndex:Int):BaseResult<List<HomeLiveEntity>>{
        return serviceApi.getHomeLiveData(pageIndex)
    }

    //分类获取直播数据
    suspend fun getClassLiveData(pageIndex: Int,classId:String):BaseResult<List<LiveItemEntity>>{
        return serviceApi.getClassLiveData(pageIndex,classId)
    }

    //获取用户数据
    suspend fun getUserInfoData(uid:String,token:String):BaseResult<List<UserInfoEntity>>{
        return serviceApi.getUserInfo(uid, token)
    }

    //用户登录
    suspend fun userLogin(user_login:String,user_pass:String):BaseResult<List<LoginEntity>>{
        return serviceApi.userLogin(user_login, user_pass,JPushUtil.getInstance().pushID)
    }

    //首页视频列表
    suspend fun getHomeVideoData(uid:String,token:String,pageIndex: Int):BaseResult<List<VideoItemEntity>>{
        return serviceApi.getHomeVideoData(uid, token, pageIndex)
    }



}