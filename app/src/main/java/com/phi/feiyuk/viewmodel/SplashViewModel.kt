package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.ext.toast
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.ConfigEntity
import com.phi.feiyuk.model.repository.LocalDataSource
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkError
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SplashViewModel(private val remoteRepo: RemoteDataSource,private val localRepo:LocalDataSource) : BaseViewModel() {

    val configLiveData = MutableLiveData<ConfigEntity>()
    fun getData() {
        launchUI({
            val uid = kv.decodeString(Const.KEY_UID, "1")
            val token = kv.decodeString(Const.KEY_TOKEN, "")

            val config = withContext(Dispatchers.IO) {
                remoteRepo.getConfig()
            }
            config.checkSuccess { list ->
                if (list.isNotEmpty()) {
                    kv.encode(Const.KEY_CONFIG, list.first())
                    //获取用户数据
                    val userInfo = withContext(Dispatchers.IO) {
                        remoteRepo.getUserInfoData(uid!!, token!!)
                    }
                    userInfo.checkSuccess {
                        kv.decodeString(Const.KEY_UID, uid)
                        kv.decodeString(Const.KEY_TOKEN, token)
                        //用户数据成功后才发送数据
                        configLiveData.value = list.first()
                        if (it.isNotEmpty()){
                            withContext(Dispatchers.IO){
                                localRepo.deleteAllUserInfo()
                                localRepo.insertUserInfo(it.first())
                            }
                        }

                    }
                    userInfo.checkError {
                        toast(it.errorMsg)
                    }
                }
            }

        }, isShowDiaLoading = true)
    }
}