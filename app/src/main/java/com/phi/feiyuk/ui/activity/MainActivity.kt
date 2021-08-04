package com.phi.feiyuk.ui.activity

import androidx.fragment.app.Fragment
import com.phi.basemodule.base.BaseVMActivity
import com.phi.feiyuk.R
import com.phi.feiyuk.config.Const
import com.phi.feiyuk.im.utils.JIMUtil

import com.phi.feiyuk.ui.fragment.HomeFragment
import com.phi.feiyuk.ui.fragment.MineFragment
import com.phi.feiyuk.ui.fragment.NearFragment
import com.phi.feiyuk.ui.fragment.RankFragment
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.utils.click
import com.phi.feiyuk.viewmodel.MainViewModel
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : BaseVMActivity<MainViewModel>() {

    private val fragmentList = mutableListOf<Fragment>()
    private var currentFragment: Fragment? = null//要显示的Fragment
    private var hideFragment: Fragment? = null//要隐藏的Fragment
    private var lastPosition: Int = 0
    private var mPosition: Int = 0

    init {
        fragmentList.run {
            add(HomeFragment())
            add(NearFragment())
            add(RankFragment())
            add(MineFragment())
        }
    }


    override fun initVM(): MainViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView() {

        setSelectedFragment(0)
        onClick()
        loginIM()
    }

    private fun onClick() {
        //选中下方的 tab
        tab_group.setTabGroupClickListener { position ->
            setSelectedFragment(position)
        }
        //开播
        btn_start.click {

        }
    }

    private fun loginIM(){
        val uid = MMKV.defaultMMKV().decodeString(Const.KEY_UID,"")
        JIMUtil.getInstance().loginImClient(uid)
    }

    private fun setSelectedFragment(position: Int) {
        when (position){
            0 ->{ DeviceUtils.setFullScreen(this, R.color.transparent, true)}
            1 ->{ DeviceUtils.setFullScreen(this, R.color.transparent, true)}
            2 ->{ DeviceUtils.setFullScreen(this, R.color.transparent, false)}
            3 ->{ DeviceUtils.setFullScreen(this, R.color.transparent, false)}
        }

        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        //要显示的fragment(解决了activity重建时新建实例的问题)
        currentFragment = fragmentManager.findFragmentByTag("fragment$position")
        hideFragment = fragmentManager.findFragmentByTag("fragment$lastPosition")

        if (position == lastPosition) {//如果位置相同
            if (currentFragment == null) {//如果fragment不存在(第一次启动应用的时候)
                currentFragment = fragmentList[position]
                currentFragment?.let { transaction.add(R.id.fl_container, it, "fragment$position") }
            }//如果位置相同，且fragment存在，则不作任何操作
        }
        if (position != lastPosition) {//如果位置不同
            //如果要隐藏的fragment存在，则隐藏
            hideFragment?.let { transaction.hide(it) }
            if (currentFragment == null) {//如果要显示的fragment不存在，则新加并提交事务
                currentFragment = fragmentList[position]
                currentFragment?.let { transaction.add(R.id.fl_container, it, "fragment$position") }
            } else {//如果要显示的存在则直接显示
                currentFragment?.let { transaction.show(it) }
                /**
                 * 存在的fragment切换根据业务 重新请求数据
                 */
                when (position) {
                    //    0 -> (currentFragment as HomeFragment).updateData()
                    //  3 -> (currentFragment as MineFragment).updateData()
                }
            }
        }
        transaction.commit()//提交事务
        lastPosition = position//更新要隐藏的fragment的位置
        mPosition = position
    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}