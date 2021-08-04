package com.phi.feiyuk.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import com.phi.brvahlib.BaseQuickAdapter
import com.phi.brvahlib.module.LoadMoreModule
import com.phi.brvahlib.viewholder.BaseViewHolder
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.LiveItemEntity
import com.phi.feiyuk.utils.GlideUtils

class HomeLiveAdapter:BaseQuickAdapter<LiveItemEntity,BaseViewHolder>(R.layout.item_homelive)/*,LoadMoreModule*/ {
    override fun convert(holder: BaseViewHolder, item: LiveItemEntity) {

        val iv_cover = holder.getView<ImageView>(R.id.iv_cover)
        val iv_lvjing = holder.getView<ImageView>(R.id.iv_lvjing)
        val iv_avatar = holder.getView<ImageView>(R.id.iv_avatar)
        val tv_num = holder.getView<TextView>(R.id.tv_num)
        val tv_name = holder.getView<TextView>(R.id.tv_name)

        GlideUtils.loadRoundImage(iv_cover,item.thumb,10)
        GlideUtils.loadRoundImage(iv_lvjing,R.drawable.bg_main_item_bottom,12)
        GlideUtils.loadImage(iv_avatar,item.avatar)
        tv_num.text = item.nums
        tv_name.text = item.user_nicename

    }
}