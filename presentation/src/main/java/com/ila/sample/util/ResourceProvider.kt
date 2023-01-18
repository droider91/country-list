package com.ila.sample.util

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author by devendra on 12/08/2022
 */
class ResourceProvider(context: Context) {

    private val appContext = context.applicationContext

    fun getDrawable(@DrawableRes resId: Int): Drawable? {
        return ContextCompat.getDrawable(appContext, resId)
    }
}