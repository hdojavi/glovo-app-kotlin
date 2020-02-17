package com.jhernando.glovomvvm.custom

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class imageAdapter {
    constructor()

    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun setImageUrl(imageView: ImageView, imageUrl: String?) {
            Picasso.with(imageView.getContext()).load(imageUrl).into(imageView)
        }
    }
}