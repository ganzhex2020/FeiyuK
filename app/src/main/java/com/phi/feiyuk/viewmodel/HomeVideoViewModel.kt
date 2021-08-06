package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.BannerEntity
import com.phi.feiyuk.model.entity.HomeLiveEntity
import com.phi.feiyuk.model.entity.LiveItemEntity
import com.phi.feiyuk.model.entity.VideoItemEntity
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class HomeVideoViewModel(private val remoteRepo: RemoteDataSource): BaseViewModel() {


    val videoData = MutableLiveData<List<VideoItemEntity>>()

    fun getHomeVideoData(pageIndex:Int){
        launchUI({
            val uid = kv.decodeString(Const.KEY_UID,"1")
            val token = kv.decodeString(Const.KEY_TOKEN,"")
            val result = withContext(Dispatchers.IO){
                remoteRepo.getHomeVideoData(uid!!,token!!,pageIndex)
            }
            result.checkSuccess {
                videoData.value = it
            }
        },isShowDiaLoading = pageIndex==1)
    }


}