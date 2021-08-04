package com.phi.feiyuk.viewmodel

import androidx.lifecycle.MutableLiveData
import com.phi.basemodule.base.BaseViewModel
import com.phi.basemodule.ext.toast
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.model.repository.RemoteDataSource
import com.phi.httplib.model.checkError
import com.phi.httplib.model.checkSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewModel(private val remoteRepo: RemoteDataSource): BaseViewModel() {

    val loginLiveData = MutableLiveData<Boolean>()

    fun login(user_login:String,user_pass:String){
        launchUI({
            val result = withContext(Dispatchers.IO){
                remoteRepo.userLogin(user_login, user_pass)
            }
            result.checkSuccess { loginList ->
                if (loginList.isNotEmpty()){
                    val userInfo = withContext(Dispatchers.IO){
                        remoteRepo.getUserInfoData(loginList.first().id,loginList.first().token)
                    }
                    userInfo.checkSuccess {
                        kv.encode(Const.KEY_UID, loginList.first().id)
                        kv.encode(Const.KEY_TOKEN, loginList.first().token)

                        loginLiveData.value = true
                    }

                }
            }
            result.checkError {
                toast(it.errorMsg)
            }
        },isShowDiaLoading = true)
    }
}