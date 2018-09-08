package com.trippl3dev.listlibrary.implementation

import com.trippl3dev.listlibrary.interfaces.IAdaptee
import java.lang.reflect.Field

abstract class  AdapteeIm<T>(list: ArrayList<T>) : IAdaptee<T> {

    val list: ArrayList<T> = ArrayList()

    override fun setList(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
    }

    override fun getList(): List<T> {
        return list
    }


    init {
        list.clear()
        list.addAll(list)
    }
}