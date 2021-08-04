package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.model.entity.BannerEntity
import com.phi.feiyuk.model.entity.HomeLiveEntity
import com.phi.feiyuk.model.entity.LiveItemEntity
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext

class HomeLiveViewModel(private val remoteRepo: RemoteDataSource): BaseViewModel() {

   // val homeData = MutableSharedFlow<HomeLiveEntity>()
    val bannerData = MutableLiveData<List<BannerEntity>>()
    val liveData = MutableLiveData<List<LiveItemEntity>>()

    fun getHomeLiveData(pageIndex:Int){
        launchUI({
            val result = withContext(Dispatchers.IO){
                remoteRepo.getHomeLiveData(pageIndex)
            }
            result.checkSuccess {
                if (it.isNotEmpty()){
                   // homeData.emit(it.first())
                   // homeData.value = it.first()
                    bannerData.value = it.first().slide
                    liveData.value = it.first().list
                }
            }
        },isShowDiaLoading = pageIndex==1)
    }

    fun getClassLiveData(pageIndex:Int,classId:String){
        launchUI({
            val result = withContext(Dispatchers.IO){
                remoteRepo.getClassLiveData(pageIndex, classId)
            }
            result.checkSuccess {
                liveData.value = it
            }
        },isShowDiaLoading = pageIndex==1)
    }
}