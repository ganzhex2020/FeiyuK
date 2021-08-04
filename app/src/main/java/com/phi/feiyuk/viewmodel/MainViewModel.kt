package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.entity.ConfigEntity
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainViewModel(private val remoteRepo: RemoteDataSource):BaseViewModel() {


}