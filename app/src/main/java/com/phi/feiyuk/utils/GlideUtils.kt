package com.phi.feiyuk.utils


import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions

import jp.wasabeef.glide.transformations.RoundedCornersTransformation

object GlideUtils {

    @JvmStatic
    fun display(res: Int, imageView: ImageView) {
        Glide.with(imageView.context).asDrawable().load(res).into(imageView)
    }
    @JvmStatic
    fun display(url: String, imageView: ImageView) {
        Glide.with(imageView.context).asDrawable().load(url).into(imageView)
    }

    @JvmStatic
    fun loadImage(imageView:ImageView,url:String){
        val options = RequestOptions().dontAnimate()/*.diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).load(url).apply(options).into(imageView)
    }

    @JvmStatic
    fun loadImage(imageView: ImageView,@DrawableRes resId: Int){
        val options = RequestOptions().centerCrop()/*.circleCrop()*/
                .dontAnimate()

        Glide.with(imageView.context).load(resId).apply(options).into(imageView)
    }


    @JvmStatic
    fun loadCircleImage(imageView: ImageView,url: String){
        val options = RequestOptions().centerCrop()
            .dontAnimate()/*.diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).load(url).apply(options).into(imageView)
    }
    @JvmStatic
    fun loadCircleImage(imageView: ImageView,@DrawableRes resId:Int){
        val options = RequestOptions().centerCrop()
            .dontAnimate()/*.diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).load(resId).apply(options).into(imageView)
    }

    @JvmStatic
    fun loadAvatar(imageView: ImageView, url: String, @DrawableRes resId:Int = 0){
        val options = RequestOptions().centerCrop().placeholder(resId).error(resId)
            .dontAnimate()/*.diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).load(url).apply(options).into(imageView)
    }

    @JvmStatic
    fun loadRoundImage(imageView: ImageView,@DrawableRes resId: Int,radius:Int=10){
        val options = RequestOptions().centerCrop()/*.circleCrop()*/
            .dontAnimate()
            .transform(RoundedCornersTransformation(radius, 0))
         //   .transform(GlideRoundTransform(15))
         /*   .diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).asDrawable().load(resId).apply(options).into(imageView)
    }
    @JvmStatic
    fun loadRoundImage(imageView: ImageView,url: String,radius:Int){
        val options = RequestOptions().centerCrop()/*.circleCrop()*/
            .dontAnimate()
            .transform(RoundedCornersTransformation(radius, 0))
        //   .transform(GlideRoundTransform(15))
        /*   .diskCacheStrategy(DiskCacheStrategy.RESOURCE)*/
        Glide.with(imageView.context).load(url).apply(options).into(imageView)
    }

}