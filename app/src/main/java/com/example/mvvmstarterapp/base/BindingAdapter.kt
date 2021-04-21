package com.example.mvvmstarterapp.base

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.mvvmstarterapp.util.Utility

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String?) {
    Utility.getCustomGlide(context)
        .load(url)
        .into(this)
}

@BindingAdapter("setTextGone")
fun TextView.setTextGone(textValue: String?) {
    if (textValue.isNullOrEmpty()) {
        visibility = View.GONE
        return
    } else {
        visibility = View.VISIBLE
    }
    text = textValue
}

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    this.isVisible = isVisible
}

@BindingAdapter("isGone")
fun View.isGone(isGone: Boolean) {
    this.isGone = isGone
}

@BindingAdapter("isInvisible")
fun View.isInvisible(isInvisible: Boolean) {
    this.isInvisible = isInvisible
}