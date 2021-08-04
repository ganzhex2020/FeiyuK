package com.phi.feiyuk.im.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImUserBean(
    val lastMessage:String,
    val unReadCount:Int,
    val lastTime:String,
    val fromType:Int,
    val msgType:Int,
    val attent:Int, //我是否关注对方
    val otherAttent:Int,//对方是否关注我
    val anchorItem:Boolean,
    val hasConversation:Boolean
):Parcelable