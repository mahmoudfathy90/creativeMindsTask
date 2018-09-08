package com.creativeapp.utils

import android.content.Context

/**
 * Created by ahmed.saad on 11/17/16.
 */

object DimensionUtils {
    fun convertPXToDP(context: Context, valueInPixels: Int): Int {
        return (valueInPixels / getMetricsDensity(context)).toInt()
    }

    fun getMetricsDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    fun convertDPToPX(context: Context, valueInDip: Int): Int {
        return (valueInDip * getMetricsDensity(context)).toInt()
    }

    fun getScreenWidthInPixels(context: Context, inPixels: Boolean): Int {
        val widthInPixles = context.resources.displayMetrics.widthPixels
        return if (inPixels) widthInPixles else convertPXToDP(context, widthInPixles)
    }

    fun getScreenHeight(context: Context, inpixels: Boolean): Int {
        val heightInPixels = context.resources.displayMetrics.heightPixels
        return if (inpixels) heightInPixels else convertPXToDP(context, heightInPixels)
    }

}
