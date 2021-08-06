package com.phi.feiyuk.model.api

import com.phi.feiyuk.model.entity.*
import com.phi.httplib.model.BaseResult
import com.phi.httplib.model.InfoTBean
import com.phi.httplib.model.OutAny
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    //获取通用配置数据
    @GET("?service=Home.getConfig")
    suspend fun getConfig(): BaseResult<List<ConfigEntity>>

    //获取首页直播数据
    @GET("?service=Home.getHot")
    suspend fun getHomeLiveData(@Query("p")pageIndex:Int):BaseResult<List<HomeLiveEntity>>

    //分类获取直播数据
    @GET("?service=Home.getClassLive")
    suspend fun getClassLiveData(@Query("p")pageIndex: Int,@Query("liveclassid")classId:String):BaseResult<List<LiveItemEntity>>

    //获取用户数据
    @GET("?service=User.getBaseInfo")
    suspend fun getUserInfo(@Query("uid")uid:String,@Query("token")token:String):BaseResult<List<UserInfoEntity>>

    //用户登录
    @GET("?service=Login.userLogin")
    suspend fun userLogin(@Query("user_login")user_login:String,@Query("user_pass")user_pass:String,@Query("pushid")pushid:String):BaseResult<List<LoginEntity>>

    @GET("?service=Video.GetVideoList")
    suspend fun getHomeVideoData(@Query("uid")uid:String,@Query("token")token:String,@Query("p")pageIndex:Int):BaseResult<List<VideoItemEntity>>
}