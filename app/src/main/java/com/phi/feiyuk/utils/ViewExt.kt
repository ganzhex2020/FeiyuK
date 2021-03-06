package com.phi.feiyuk.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat

fun View.click(block: () -> Unit) {
    setOnClickListener {
        block()
    }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.background(drawable: Drawable) {
    ViewCompat.setBackground(this, drawable)
}