package com.innocent.languagecommunity.ui.helpers

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide



object BindingAdapters {

    @JvmStatic
    @BindingAdapter("pictureUrl")
    fun ImageView.loadImage(url: String?) {
        if (url.isNullOrEmpty()) return
        Glide.with(this).load(url).into(this)
    }

    @JvmStatic
    @BindingAdapter("visibilityForNewIcon")
    fun visibilityForNewIcon(imageView: ImageView, value: Int?) {
        value?.let {
            if (value == 0) {
                imageView.visibility = View.VISIBLE
            } else {
                imageView.visibility = View.INVISIBLE
            }
        }
    }

    @JvmStatic
    @BindingAdapter("visibilityForLikeButtonClicked")
    fun visibilityForLikeButtonClicked(imageView: AppCompatImageView, value: Boolean?) {
        value?.let {
            if (value == true) {
                imageView.visibility = View.VISIBLE
            } else {
                imageView.visibility = View.INVISIBLE
            }
        }
    }


    @JvmStatic
    @BindingAdapter("visibilityForReferenceCntText")
    fun visibilityForReferenceCntText(textView: TextView, value: Int?) {
        value?.let {
            if (value > 0) {
                textView.visibility = View.VISIBLE
                textView.text = value.toString()
            } else {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}
