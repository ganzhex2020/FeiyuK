package com.phi.feiyuk.ui.activity

import com.gyf.immersionbar.ktx.immersionBar
import com.phi.basemodule.base.BaseVMActivity
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.UserInfoEntity
import com.phi.feiyuk.utils.GlideUtils
import com.phi.feiyuk.utils.click
import com.phi.feiyuk.viewmodel.UserProfileViewModel
import kotlinx.android.synthetic.main.activity_userprofile.*
import kotlinx.android.synthetic.main.layout_commbar.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class UserProfileActivity : BaseVMActivity<UserProfileViewModel>() {


    override fun initVM(): UserProfileViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.activity_userprofile

    override fun initView() {
        immersionBar {
            statusBarDarkFont(true)
            statusBarColor(R.color.white)
            fitsSystemWindows(true)
        }
        tv_title.text = "编辑资料"
        iv_back.click { onBackPressed() }
    }

    override fun initData() {
        mViewModel.getUserInfoData()

    }

    override fun startObserve() {
        mViewModel.userInfoData.observe(this,{
            if (it !=null&&it.isNotEmpty()){
                setUserInfo(it.first())
            }
        })
    }
    private fun setUserInfo(it: UserInfoEntity){
        GlideUtils.loadImage(iv_avatar,it.avatar)
        tv_name.text = it.user_nicename
        tv_sign.text = it.signature
        tv_birthday.text = it.birthday
        tv_sex.text = if (it.sex == "1"){"男"}else{"女"}

    }
}