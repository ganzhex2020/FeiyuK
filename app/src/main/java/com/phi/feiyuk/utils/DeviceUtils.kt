package com.phi.feiyuk.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat

object DeviceUtils {
    @JvmStatic
    fun getCNM(n: Int, m: Int): Int {
        return if (m == 1) {
            n
        } else if (n <= m&&m>1) {
            1
        } else {
            getCNM(n - 1, m - 1) + getCNM(n - 1, m)
        }
    }

    @JvmStatic
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    @JvmStatic
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    @JvmStatic
    fun setFullScreen(activity: Activity, colorStatusBar: Int, isDark: Boolean) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        val visibility = if (isDark) {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_VISIBLE
        }
        window.decorView.systemUiVisibility = visibility
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, colorStatusBar)

    }
}