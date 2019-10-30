package com.michael.lib_core.ext

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.load(imageUri: String) {
    Glide.with(context)
        .load(imageUri)
        .apply(RequestOptions())
        .into(this)
}

fun ImageView.load(@DrawableRes id: Int) {
    Glide.with(context)
        .load(id)
        .apply(RequestOptions())
        .into(this)
}