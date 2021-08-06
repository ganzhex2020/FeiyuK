package com.phi.feiyuk.ui.activity

import android.content.Intent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.gyf.immersionbar.ktx.immersionBar
import com.phi.basemodule.base.BaseVMActivity
import com.phi.basemodule.utils.LogUtils
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.AdEntity
import com.phi.feiyuk.utils.DeviceUtils
import com.phi.feiyuk.utils.GlideUtils
import com.phi.feiyuk.utils.click
import com.phi.feiyuk.utils.visible
import com.phi.feiyuk.viewmodel.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SplashActivity : BaseVMActivity<SplashViewModel>() {

    override fun initVM(): SplashViewModel = getViewModel()

    override fun getLayoutResId(): Int = R.layout.activity_splash


    override fun initView() {

    //    DeviceUtils.setFullScreen(this, R.color.transparent, false)
        //   val statusBarHeight = DeviceUtils.getStatusBarHeight(this)
        //    cl_parent.setPadding(0, statusBarHeight, 0, 0)
        immersionBar {
        //    transparentBar()
            fullScreen(true)
        }
        GlideUtils.display(R.mipmap.ic_splash, iv_splash)

        cl_skip.click {
            gotoMain()
        }
    }

    override fun initData() {
        mViewModel.getData()
    }

    override fun startObserve() {
        mViewModel.run {
            configLiveData.observe(this@SplashActivity, {
                if (it.guide.type == "0") {
                    cl_skip.visible()
                    playAd(it.guide.list)
                }
            })
        }
    }

    //跳到MainActivity
    private fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun playAd(list: List<AdEntity>) {
        var lastImgIndex = -1
        val length = 2000 * list.size / 200
        progress.setMaxProgress(length.toFloat())
        // 使用通道创建定时操作,和Go协程一样的.
        lifecycleScope.launchWhenResumed {
            //创建通道
            val channel = Channel<Int>()
            // 2.向通道发送数据
            launch(Dispatchers.IO) {
                for (i in 1..length) {
                    delay(200)
                    channel.send(i)
                }
                channel.close()
            }
            // 3.通道接收数据
//            launch(Dispatchers.IO){
//            }
            for (n in channel) {
                val index = n / 10
                progress.setCurProgress(n.toFloat())
                //显示图片
                if (lastImgIndex != index && index < list.size) {
                    lastImgIndex = index
                    GlideUtils.display(list[lastImgIndex].thumb, iv_splash)
                }
                //进度条结束 跳到MainActivity
                if (n == length) {
                    gotoMain()
                }
            }

        }


    }
}