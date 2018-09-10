package com.trippl3dev.listlibrary.interfaces

import android.arch.lifecycle.LiveData
import android.content.Context
import android.databinding.ViewDataBinding
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.trippl3dev.listlibrary.snap.GravityPagerSnapHelper

import java.util.ArrayList

interface IListVM<From,T, in V : IListCallback> {

    fun setAdaptee(adapter: IAdaptee<T>)
    fun setOp(operation: IListOp<T>)
    fun getListOp(): IListOp<T>
    fun getAdaptee(): IAdaptee<T>
    fun getViewId(type: Int): Int
    fun onBindView(root: View?, position: Int) {}

    fun attachSnapHelper():Boolean{
        return false
    }

    fun hasSwiptoRefresh():Boolean{
        return false
    }


    fun mapFrom(it: From): T

    fun getLoadingViewID(): Int

    fun getErrorViewID(): Int

    fun setPageStartIndex():Int

    fun isInNestedScroll():Boolean

    fun getViewType(position: Int): Int {
        return 0
    }

    fun getListLayoutManager(context: Context):RecyclerView.LayoutManager?

    fun setIListCallback(callback: V) {

    }

    fun getLiveDataList(): LiveData<ArrayList<T>>

    fun onViewSnapped(view: View, position: Int)
    fun getSnapHelper(): GravityPagerSnapHelper
    fun fetchData() {

    }

    fun isCircular(): Boolean {
        return false
    }

    fun hasLoadMore(): Boolean {
        return false
    }

    fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

    fun filter(value: Any): List<T>?
    fun filterCondition(value: Any, it: T): Boolean {
        return true
    }

    fun setBindiningModel(binding: ViewDataBinding?)
}