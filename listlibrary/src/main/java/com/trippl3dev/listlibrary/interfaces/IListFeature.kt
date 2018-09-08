
package com.trippl3dev.listlibrary.interfaces

import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.RecyclerView
import android.widget.ScrollView


interface IListFeature {

    fun setHaFixedSize(fixed: Boolean)
    fun setListVMCallback(listCallback:IListCallback)

    fun changeVM(className: String)
    fun resetData()
    fun setRecyclerListener(listener: RecyclerView.RecyclerListener)

    fun addOnChildAttachStateChangeListener(listener: RecyclerView.OnChildAttachStateChangeListener)

    fun clearOnChildAttachStateChangeListeners()

    fun addItemDecorator(decorator:RecyclerView.ItemDecoration)


    fun setLayoutManager(layout: RecyclerView.LayoutManager)

    fun getLayoutManager(): RecyclerView.LayoutManager?

    fun setOnFlingListener(onFlingListener: RecyclerView.OnFlingListener?)


    fun setRecycledViewPool(pool: RecyclerView.RecycledViewPool)

    fun addItemDecoration(decor: RecyclerView.ItemDecoration)

    fun addOnScrollListener(listener: RecyclerView.OnScrollListener)

    fun clearOnScrollListeners()

    fun scrollToPosition(position: Int)

    fun smoothScrollToPosition(position: Int)

    fun stopScroll()

    fun addOnItemTouchListener(listener: RecyclerView.OnItemTouchListener)

    fun removeOnItemTouchListener(listener: RecyclerView.OnItemTouchListener)

    fun requestDisallowInterceptTouchEvent(disallowIntercept: Boolean)

    fun setItemAnimator(animator: RecyclerView.ItemAnimator)

//    fun enableSnapeHelper()
    fun filter( value:Any)
    fun setState(state:Int)
    fun setScrollView(scrollView: NestedScrollView)
    fun scrollTo(x: Int, y: Int)
}