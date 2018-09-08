package com.creativeapp.ui.designtask.myfragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.creative.domain.model.List_Domain
import com.creativeapp.R
import com.creativeapp.ui.base.activity.ListCallback

import com.trippl3dev.listlibrary.implementation.FullListVM


class List_ItemVM : FullListVM<List_Domain, List_Model, List_ItemVM.MyListCallBack>() {
    override fun mapFrom(it: List_Domain): List_Model {
        return List_Model(it.id, it.image, it.title, it.time, it.sex, it.type, it.number)
    }


    override fun getViewId(type: Int): Int = R.layout.my_list_item


    override fun onViewSnapped(view: View, position: Int) {
    }


    interface MyListCallBack : ListCallback {
        fun onItemSelected(t: List_Model)
    }

    override fun hasLoadMore(): Boolean = false

    override fun getLayoutManager(context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }

    override fun fetchData() {
        super.fetchData()
        listCallback.accept(0)
    }

    override fun onBindView(root: View?, position: Int) {
        super.onBindView(root, position)
        root?.setOnClickListener {
            listCallback.onItemSelected(getListOp().getList()?.get(position)!!)
        }
    }


}