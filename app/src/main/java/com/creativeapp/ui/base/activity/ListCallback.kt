package com.creativeapp.ui.base.activity

import com.trippl3dev.listlibrary.interfaces.IListCallback

interface ListCallback :IListCallback{
    fun accept(currentPage: Int){}
}
