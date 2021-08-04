package com.phi.feiyuk.ui.adapter

import com.phi.feiyuk.model.entity.BannerEntity
import com.phi.feiyuk.utils.GlideUtils
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

/**
 *Author:ganzhe
 *时间:2021/3/11 13:27
 *描述:This is HomeBannerAdapter
 */
class HomeLiveBannerAdapter(dataList:List<BannerEntity>): BannerImageAdapter<BannerEntity>(dataList) {

    override fun onBindView(holder: BannerImageHolder?, data: BannerEntity?, position: Int, size: Int) {
        if (holder?.itemView!=null&&data!=null){
           // GlideUtils.loadImage(holder.imageView, R.mipmap.ic_banner)

            GlideUtils.loadImage(holder.imageView,data.slide_pic)

        }
    }
}