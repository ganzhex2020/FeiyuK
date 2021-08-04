package com.phi.httplib.model

data class InfoTBean(
    val address: String,
    val apk_des: String,
    val apk_ewm: String,
    val apk_url: String,
    val apk_ver: String,
    val app_android: String,
    val app_ios: String,
    val cloudtype: String,
    val copyright: String,
    val guide: Guide,
    val ios_shelves: String,
    val ipa_des: String,
    val ipa_ewm: String,
    val ipa_url: String,
    val ipa_ver: String,
    val isup: String,
    val letter_switch: String,
    val level: List<Level>,
    val levelanchor: List<Levelanchor>,
    val live_time_coin: List<Any>,
    val live_type: List<List<String>>,
    val liveclass: List<Liveclas>,
    val login_type: List<Any>,
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
)

data class Guide(
    val list: List<Banner>,
    val switch: String,
    val time: String,
    val type: String
)

data class Level(
    val bg: String,
    val colour: String,
    val levelid: String,
    val thumb: String,
    val thumb_mark: String
)

data class Levelanchor(
    val bg: String,
    val levelid: String,
    val thumb: String,
    val thumb_mark: String
)

data class Liveclas(
    val des: String,
    val id: String,
    val name: String,
    val orderno: String,
    val thumb: String
)

data class Banner(
    val href: String,
    val thumb: String
)