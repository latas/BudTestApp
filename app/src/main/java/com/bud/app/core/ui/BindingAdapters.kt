package com.bud.app.core.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("visibility")
fun View.setVisibility(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

@BindingAdapter("textColor")
fun TextView.textColor(textColor: Int) {
    if (textColor == 0)
        return
    this.let {
        it.setTextColor(ContextCompat.getColor(it.context, textColor))
    }
}