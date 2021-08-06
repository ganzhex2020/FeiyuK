package com.phi.feiyuk.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.phi.brvahlib.BaseQuickAdapter
import com.phi.brvahlib.module.LoadMoreModule
import com.phi.brvahlib.viewholder.BaseViewHolder
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.LiveItemEntity
import com.phi.feiyuk.model.entity.VideoItemEntity
import com.phi.feiyuk.utils.GlideUtils

class HomeVideoAdapter:BaseQuickAdapter<VideoItemEntity,BaseViewHolder>(R.layout.item_homevideo)/*,LoadMoreModule*/ {
    override fun convert(holder: BaseViewHolder, item: VideoItemEntity) {

        val iv_cover = holder.getView<ImageView>(R.id.iv_cover)
        //val iv_lvjing = holder.getView<ImageView>(R.id.iv_lvjing)
        val iv_avatar = holder.getView<ImageView>(R.id.iv_avatar)
        val tv_num = holder.getView<TextView>(R.id.tv_num)
        val tv_name = holder.getView<TextView>(R.id.tv_name)
        val tv_title = holder.getView<TextView>(R.id.tv_title)

        GlideUtils.loadImage(iv_cover,item.thumb)
   //     GlideUtils.loadRoundImage(iv_lvjing,R.drawable.bg_main_item_bottom,12)
        GlideUtils.loadImage(iv_avatar,item.userinfo.avatar)
        tv_num.text = item.views
        tv_name.text = item.userinfo.user_nicename
        tv_title.text = item.title

    }
}