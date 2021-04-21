package com.example.mvvmstarterapp.util

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.mvvmstarterapp.R

object Utility {

    // Example : Utility.getCustomGlide(context).load("").into(imageView)
    fun getCustomGlide(context: Context): RequestManager {
        return Glide.with(context).applyDefaultRequestOptions(requestOptions(context))
    }

    fun getCustomGlide(view: View): RequestManager {
        return Glide.with(view.context).applyDefaultRequestOptions(requestOptions(view.context))
    }

    private fun requestOptions(context: Context): RequestOptions {
        val placeHolder = CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }
        return RequestOptions()
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .placeholder(placeHolder)
            .error(R.drawable.image_error)
    }

    /**
     * It opens the given url with Chrome custom tabs
     */
    fun openWebSiteWithCustomTabs(context: Context, url: String) {
        val colorSchemeBuilder = CustomTabColorSchemeParams.Builder().run {
            setToolbarColor(ContextCompat.getColor(context, R.color.purple_500))
                .build()
        }
        val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder().run {
            setDefaultColorSchemeParams(colorSchemeBuilder)
            setStartAnimations(context, R.anim.slide_in_right, R.anim.slide_out_left)
            setExitAnimations(context, R.anim.slide_in_left, R.anim.slide_out_right)
        }
        val tabIntent = builder.build()
        tabIntent.launchUrl(context, Uri.parse(url))
    }
}