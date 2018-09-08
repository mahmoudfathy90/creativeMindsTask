package com.creativeapp.utils


import android.databinding.BindingAdapter
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.*
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.*
import android.widget.*
import com.creativeapp.R

import com.creativeapp.application.BaseUrl
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("align")
fun View.align(arabic: Boolean) {
    val layoutParams = layoutParams as RelativeLayout.LayoutParams
    if (arabic) {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
    } else {
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
    }
    setLayoutParams(layoutParams)
}

@BindingAdapter("rotattoDirection")
fun View.alignusingRotation(arabic: Boolean) {
    rotationY = if (arabic) {
        180f
    } else {
        0f
    }
}

@BindingAdapter("direction")
fun View.direction(arabic: Boolean) {
    if (arabic) {
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_RTL)
    } else {
        ViewCompat.setLayoutDirection(this, ViewCompat.LAYOUT_DIRECTION_LTR)
    }
}

@BindingAdapter("gravity")
fun View.gravity(arabic: Boolean) {
    if (this is EditText) {
        gravity = if (arabic) {
            Gravity.RIGHT
        } else {
            Gravity.LEFT
        }
    } else if (this is TextView) {
        gravity = if (arabic) {
            Gravity.RIGHT
        } else {
            Gravity.LEFT
        }
    } else if (this is LinearLayout) {
        gravity = if (arabic) {
            Gravity.RIGHT
        } else {
            Gravity.LEFT
        }
    } else if (this is NavigationView) {
        val params = getLayoutParams() as DrawerLayout.LayoutParams
        if (arabic) {
            params.gravity = Gravity.RIGHT
        } else {
            params.gravity = Gravity.LEFT
        }
    }
}


@BindingAdapter("drawableLeftDirection")
fun View.drawable(arabic: Boolean?) {
    var left: Drawable? = null
    if (this is TextView) {
        left = compoundDrawables[0]
        if (arabic!!) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, left, null)
        } else {
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null)
        }
    } else if (this is Button) {
        left = compoundDrawables[2]
        if (arabic!!) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, left, null)
        } else {
            setCompoundDrawablesWithIntrinsicBounds(left, null, null, null)
        }
    }

}


@BindingAdapter("setImage")
fun ImageView.setImage(url: String?) {
    if (url != null)
        Picasso.with(this.context)
                .load(if (url?.contains("http://".toRegex())!!) url else "$BaseUrl$url").into(this)
}


@BindingAdapter("setLineInCenter")
fun TextView.putLineCenterTextView(isCenter: Boolean) {
    this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

const val SELECTED: Int = 2
const val UNSELECTED: Int = 1
const val INDECATOR: Int = 0

@BindingAdapter("setRatingBarStarYellow")
fun RatingBar.setRatingBarStarYellow(@ColorRes color: Int) {
    var layerDrawable: LayerDrawable = this.progressDrawable as LayerDrawable
    layerDrawable.getDrawable(SELECTED).setColorFilter(ContextCompat.getColor(context, color), PorterDuff.Mode.SRC_ATOP)
    layerDrawable.getDrawable(UNSELECTED).setColorFilter(ContextCompat.getColor(context, android.R.color.darker_gray), PorterDuff.Mode.SRC_ATOP)
    layerDrawable.getDrawable(INDECATOR).setColorFilter(ContextCompat.getColor(context, android.R.color.holo_blue_bright), PorterDuff.Mode.SRC_ATOP)
}

@BindingAdapter("prefix")
fun TextView.prefix(@StringRes resourc: String) {
    this.text = "$resourc ${this.text}"
}

@BindingAdapter("suffix")
fun TextView.suffix(resourc: String) {
    this.text = "${this.text} $resourc"
}


//@BindingAdapter("setBackground")
//fun TextView.setBackground(selected: Boolean) {
//    if (selected)
//        this.background = context.resources.getDrawable(R.drawable.hospital_details_button)
//}

@BindingAdapter("setMandatory")
fun TextView.setMandatory(add: Boolean) {
    if (!add) return

    var textViewText: String = this.text.toString()

    var myText = "*  $textViewText"

    // textViewText= textViewText[0].toString()
    val wordSpan = SpannableString(myText)
    wordSpan.setSpan(ForegroundColorSpan(Color.RED), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    this.text = wordSpan
}

@BindingAdapter("percent", "color")
fun ProgressBar.setProgressColorForProfile(progress: Int, color: Int) {
    val colorPosition = (progress / 10)
    this.progress = colorPosition
    var progressBarDrawable: LayerDrawable = this.progressDrawable as LayerDrawable
    var progressDrawable: Drawable = progressBarDrawable.getDrawable(1)
    progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)
}


@BindingAdapter("setRoundBackGround")
fun CheckBox.setRoundBackGround(radius: Float) {
    var stringBuilder: SpannableStringBuilder = SpannableStringBuilder()
    stringBuilder.append(" " + this.text + " ")
    stringBuilder.setSpan(RoundedBackgroundSpan(this.context, radius), 0, stringBuilder.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


    this.text = stringBuilder

}


@BindingAdapter("setCurentdateAndTime")
fun TextView.setCurentdateAndTime(isDate: Boolean) {
    val calendar = Calendar.getInstance()
    val dateAndTime = SimpleDateFormat("yyyy - MM - dd h:mm a", Locale.ENGLISH)
    val date = SimpleDateFormat("yyyy - MM - dd ", Locale.ENGLISH)
    val time = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    val strDate = dateAndTime.format(calendar.time)
    this.text = strDate
}


@BindingAdapter("backgroundDrawableColor")
fun View.setBackgroundDrawableColor(color: Int) {
    val background = background
    when (background) {
        is ShapeDrawable -> background.paint.color = color
        is GradientDrawable -> background.setColor(color)
        is ColorDrawable -> background.color = color
    }
}


fun View.setViewState(xmlLayout: Int, colorRes: Int, hide: Boolean) {
    val view: View = LayoutInflater.from(context).inflate(xmlLayout, null)
    val loadingContainer = LinearLayout(context)
    loadingContainer.layoutParams = this.layoutParams
    loadingContainer.setBackgroundColor(ContextCompat.getColor(context, colorRes))
    loadingContainer.addView(view)
    loadingContainer.id = this.id - 1
    loadingContainer.gravity = Gravity.CENTER
    (this.parent as ViewGroup).addView(loadingContainer)
    this.visibility = if (hide) View.INVISIBLE else View.VISIBLE
}


fun NestedScrollView.setLister(listener: RecyclerView.OnScrollListener, recyclerView: RecyclerView) {
    this.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener
    { v, scrollX, scrollY, oldScrollX, oldScrollY ->
        listener.onScrolled(recyclerView, scrollX, scrollY)
    })



    fun ProgressBar.changeColor(color: Int) {
        var progressBarDrawable: LayerDrawable = this.progressDrawable as LayerDrawable
        var progressDrawable: Drawable = progressBarDrawable.getDrawable(1)
        progressDrawable.setColorFilter(color, PorterDuff.Mode.SRC_IN)

    }


}
