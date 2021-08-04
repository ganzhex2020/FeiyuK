package com.phi.feiyuk.ui.activity

import android.content.Intent
import com.phi.basemodule.base.BaseVMActivity
import com.phi.basemodule.ext.toast
import com.phi.feiyuk.R
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.utils.RegularUtils
import com.phi.feiyuk.utils.click
import com.phi.feiyuk.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class LoginActivity:BaseVMActivity<LoginViewModel>() {

    override fun initVM(): LoginViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.activity_login

    override fun initView() {
        DeviceUtils.setFullScreen(this, R.color.transparent, false)
        cl_parent.setPadding(0, DeviceUtils.getStatusBarHeight(this), 0, 0)
        click()

    }

    private fun click(){
        btn_login.click {
            val phone = edit_phone.text.toString().trim()
            val passwd = edit_pwd.text.toString().trim()

            if (phone.isBlank()){
                toast("请输入手机号")
                edit_phone.requestFocus()
                return@click
            }
            if (!RegularUtils.validateMobileNumber(phone)){
                toast("请输入正确的手机号")
                edit_phone.requestFocus()
                return@click
            }
            if (passwd.isBlank()){
                toast("请输入密码")
                edit_pwd.requestFocus()
                return@click
            }
            mViewModel.login(phone,passwd)

        }
    }

    override fun initData() {

    }

    override fun startObserve() {
        mViewModel.run {
            loginLiveData.observe(this@LoginActivity,{
                if (it){
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    this@LoginActivity.startActivity(intent)
                    finish()
                }
            })
        }
    }
}