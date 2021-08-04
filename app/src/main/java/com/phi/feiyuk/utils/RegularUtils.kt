package com.phi.feiyuk.utils

import java.util.regex.Pattern

object RegularUtils {

    //判断手机号码的正则表达式
    private const val MOBILE_NUM_REGEX = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(16[0-9])|(19[0-9]))\\d{8}$"

    /**
     * 验证一个号码是不是手机号，以前这个地方是用正则判断的，现在改由服务端验证
     *
     * @param mobileNumber
     */
    @JvmStatic
    fun validateMobileNumber(mobileNumber: String): Boolean {
        val p = Pattern.compile(MOBILE_NUM_REGEX)
        val m = p.matcher(mobileNumber)
        return m.matches()
    }
}