package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.BannerEntity
import com.phi.feiyuk.model.entity.HomeLiveEntity
import com.phi.feiyuk.model.entity.LiveItemEntity
import com.phi.feiyuk.model.entity.UserInfoEntity
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class MineViewModel(private val remoteRepo: RemoteDataSource): BaseViewModel() {


    val userInfoLiveData = MutableLiveData<UserInfoEntity>()


    fun getUserInfoData(){
        launchUI({
            val uid = kv.decodeString(Const.KEY_UID,"1")
            val token = kv.decodeString(Const.KEY_TOKEN,"")
            val result = withContext(Dispatchers.IO){
                remoteRepo.getUserInfoData(uid!!,token!!)
            }
            result.checkSuccess {
                if (it.isNotEmpty()){
                    userInfoLiveData.value = it.first()
                }
            }
        })
    }


}