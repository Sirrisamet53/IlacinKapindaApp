package com.h5190047.ilacinkapinda.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

//Resimlerin Gösterileceği nesne Sınıftır
object GlideUtil {
    fun resmiIndiripGoster(context: Context, url: String, image: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(image)
    }
}