package com.phi.feiyuk.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import com.phi.brvahlib.BaseQuickAdapter
import com.phi.brvahlib.viewholder.BaseViewHolder
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.LiveclasEntity
import com.phi.feiyuk.utils.GlideUtils

class HomeLiveTopAdapter:BaseQuickAdapter<LiveclasEntity,BaseViewHolder>(R.layout.item_homelive_top) {


    override fun convert(holder: BaseViewHolder, item: LiveclasEntity) {
        val iv_class = holder.getView<ImageView>(R.id.iv_class)
        val tv_class = holder.getView<TextView>(R.id.tv_class)

        if (item.id == "0"){
            GlideUtils.loadImage(iv_class,R.drawable.ic_class_all)
        }else{
            GlideUtils.loadImage(iv_class,item.thumb)
        }
        tv_class.text = item.name
        tv_class.isSelected = item.select
    }
}