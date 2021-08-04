package com.phi.feiyuk.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import com.phi.brvahlib.BaseQuickAdapter
import com.phi.brvahlib.BaseSectionQuickAdapter
import com.phi.brvahlib.viewholder.BaseViewHolder
import com.phi.feiyuk.R
import com.phi.feiyuk.model.entity.MyEntity
import com.phi.feiyuk.utils.GlideUtils

class MyItemAdapter:BaseSectionQuickAdapter<MyEntity,BaseViewHolder>(R.layout.item_myitem_header,R.layout.item_myitem) {

    override fun convertHeader(helper: BaseViewHolder, item: MyEntity) {

    }

    override fun convert(holder: BaseViewHolder, item: MyEntity) {
        val thumb = holder.getView<ImageView>(R.id.thumb)
        val name = holder.getView<TextView>(R.id.name)

        name.text = item.name
        GlideUtils.loadImage(thumb,item.thumb)
    }
}