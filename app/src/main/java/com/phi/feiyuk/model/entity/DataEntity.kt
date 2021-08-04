package com.phi.feiyuk.model.entity

import android.os.Parcelable
import com.phi.brvahlib.entity.SectionEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ConfigEntity(
    val address: String,
    val apk_des: String,
    val apk_ewm: String,
    val apk_url: String,
    val apk_ver: String,
    val app_android: String,
    val app_ios: String,
    val cloudtype: String,
    val copyright: String,
    val guide: GuideEntity,
    val ios_shelves: String,
    val ipa_des: String,
    val ipa_ewm: String,
    val ipa_url: String,
    val ipa_ver: String,
    val isup: String,
    val letter_switch: String,
    val level: List<LevelEntity>,
    val levelanchor: List<LevelanchorEntity>,
    //val live_time_coin: List<Any>,
    val live_type: List<List<String>>,
    val liveclass: List<LiveclasEntity>,
    //val login_type: List<Any>,
    val maintain_switch: String,
    val maintain_tips: String,
    val mobile: String,
    val name_coin: String,
    val name_votes: String,
    val qiniu_domain: String,
    val qr_url: String,
    val share_des: String,
    val share_title: String,
    val share_type: List<String>,
    val site: String,
    val sitename: String,
    val sprout_eye: String,
    val sprout_face: String,
    val sprout_key: String,
    val sprout_pink: String,
    val sprout_saturated: String,
    val sprout_skin: String,
    val sprout_white: String,
    val txcloud_appid: String,
    val txcloud_bucket: String,
    val txcloud_region: String,
    val tximgfolder: String,
    val txvideofolder: String,
    val video_audit_switch: String,
    val video_share_des: String,
    val video_share_title: String,
    val wx_siteurl: String
):Parcelable

@Parcelize
data class GuideEntity(
    val list: List<AdEntity>,
    val switch: String,
    val time: String,
    val type: String
):Parcelable
@Parcelize
data class LevelEntity(
    val bg: String,
    val colour: String,
    val levelid: String,
    val thumb: String,
    val thumb_mark: String
):Parcelable
@Parcelize
data class LevelanchorEntity(
    val bg: String,
    val levelid: String,
    val thumb: String,
    val thumb_mark: String
):Parcelable
@Parcelize
data class LiveclasEntity(
    val des: String,
    val id: String,
    val name: String,
    val orderno: String,
    val thumb: String,
    var select:Boolean
):Parcelable
@Parcelize
data class AdEntity(
    val href: String,
    val thumb: String
):Parcelable

data class HomeLiveEntity(
    val slide: List<BannerEntity>,
    val list: List<LiveItemEntity>
)

data class LiveItemEntity(
    val anyway: String,
    val avatar: String,
    val avatar_thumb: String,
    val city: String,
    val goodnum: String,
    val hotvotes: String,
    val isshop: String,
    val isvideo: String,
    val level: String,
    val level_anchor: String,
    val nums: String,
    val pull: String,
    val sex: String,
    val starttime: String,
    val stream: String,
    val thumb: String,
    val title: String,
    val type: String,
    val type_val: String,
    val uid: String,
    val user_nicename: String
)

data class BannerEntity(
    val slide_pic: String,
    val slide_url: String
)
data class UserInfoEntity(
    val agent_switch: String,
    val avatar: String,
    val avatar_thumb: String,
    val birthday: String,
    val city: String,
    val coin: String,
    val consumption: String,
    val family_switch: String,
    val fans: String,
    val follows: String,
    val id: String,
    val level: String,
    val level_anchor: String,
    val liang: Liang,
    val list: List<List<MyEntity>>,
    val lives: Int,
    val location: String,
    val province: String,
    val sex: String,
    val signature: String,
    val user_nicename: String,
    val vip: Vip,
    val votes: String,
    val votestotal: String
)

data class Liang(
    val name: String
)

data class MyEntity(
    val href: String="",
    val id: String="",
    val name: String="",
    val thumb: String="",
    override var isHeader: Boolean = false
):SectionEntity

data class Vip(
    val type: String
)

data class LoginEntity(
    val id: String,
    val token: String,
    val avatar: String,
    val avatar_thumb: String,
    val birthday: String,
    val city: String,
    val coin: String,
    val consumption: String,
    val expiretime: String,
    val isagent: String,
    val isreg: String,
    val last_login_time: String,
    val level: String,
    val level_anchor: String,
    val location: String,
    val login_type: String,
    val province: String,
    val sex: String,
    val signature: String,
    val user_nicename: String,
    val user_type: String,
    val votestotal: String
)